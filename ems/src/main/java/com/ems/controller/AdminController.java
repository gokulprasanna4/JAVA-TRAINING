package com.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ems.modal.Attendence;
import com.ems.modal.User;
import com.ems.repo.AttendenceRepo;
import com.ems.repo.UserRepo;

@RestController
@RequestMapping("/ems/manager")
public class AdminController {

    @Autowired private UserRepo userRepo;
    @Autowired private AttendenceRepo attendenceRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    // --- USER CRUD ---
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepo.findByUserId(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()){
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            user.setRole(userDetails.getRole());
            user.setReportingId(userDetails.getReportingId());
            user.setActive(userDetails.isActive());
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    // --- APPROVALS ---
    @GetMapping("/leaves/pending")
    public ResponseEntity<List<Attendence>> getPendingLeaves() {
        List<Attendence> pending = attendenceRepo.findAll().stream()
                .filter(a -> a.getStatus() == Attendence.Status.PENDING)
                .toList();
        return new ResponseEntity<>(pending, HttpStatus.OK);
    }

    @PutMapping("/leave/status/{requestId}")
    public ResponseEntity<?> updateLeaveStatus(
            @PathVariable Long requestId, 
            @RequestParam String status, 
            @RequestParam(required = false) String managerComment) {
        
        return attendenceRepo.findById(requestId).map(req -> {
            try {
                req.setStatus(Attendence.Status.valueOf(status.toUpperCase()));
                req.setManagerComment(managerComment);
                attendenceRepo.save(req);
                return new ResponseEntity<>(req, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Invalid Status", HttpStatus.BAD_REQUEST);
            }
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}