package com.ems.repo;
import com.ems.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
    Optional<User> findByUsername(String username);
}























//package com.ems.repo;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.ems.modal.User;
//import java.util.List;
//
//
//public interface UserRepo extends JpaRepository<User, Long> {
//	
//    Optional<User> findByUsername(String username);
//    
//    Optional<User> findByUserId(Long userId);
//    List<User> findByReportingId(Long reportingId);
//    
//   
//    
//}

