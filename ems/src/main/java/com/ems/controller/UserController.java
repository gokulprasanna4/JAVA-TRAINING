package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ems.modal.*;
import com.ems.repo.*;

@RestController
@RequestMapping("/ems/user")
public class UserController {

    @Autowired private UserRepo userRepo;
    @Autowired private AttendenceRepo attendenceRepo;
    @Autowired private FeedbackRepo feedbackRepo;
    @Autowired private InfoRequestRepo infoRequestRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepo.findByUserId(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/apply/attendence")
    public ResponseEntity<?> applyAttendence(@RequestParam Long userId, @RequestBody Attendence attendence) {
        User user = userRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        attendence.setUserId(userId);
        attendence.setReportingId(user.getReportingId());
        attendence.setStatus(Attendence.Status.PENDING);
        
        if (attendence.getAttendenceType() == null) {
             return new ResponseEntity<>("Attendance Type is required", HttpStatus.BAD_REQUEST);
        }

        attendenceRepo.save(attendence);
        return new ResponseEntity<>(attendence, HttpStatus.OK);
    }

    @PostMapping("/submit/feedback")
    public ResponseEntity<?> submitFeedback(@RequestParam Long userId, @RequestBody Feedback feedback) {
        feedback.setUserId(userId);
        feedbackRepo.save(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
    
    @PostMapping("/submit/info-request")
    public ResponseEntity<?> submitInfoRequest(@RequestParam Long userId, @RequestBody InfoRequest infoRequest){
        infoRequest.setUserId(userId);
        infoRequest.setStatus(InfoRequest.Status.SUBMITTED);
        infoRequestRepo.save(infoRequest);
        return new ResponseEntity<>(infoRequest, HttpStatus.OK);
    }

    @GetMapping("/applied/attendence")
    public ResponseEntity<?> getAppliedAttendence(@RequestParam Long userId){
        return new ResponseEntity<>(attendenceRepo.findByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/submitted/feedback")
    public ResponseEntity<?> getSubmittedFeedback(@RequestParam Long userId){
        return new ResponseEntity<>(feedbackRepo.findByUserId(userId), HttpStatus.OK);
    }
}