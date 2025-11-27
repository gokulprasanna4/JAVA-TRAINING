package com.ems.repo;
import com.ems.modal.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserId(Long userId);
}


























//package com.ems.repo;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.ems.modal.Feedback;
//import java.util.List;
//
//
//public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
//	
//	List<Feedback> findByFeedbackId(Long feedbackId);
//	List<Feedback> findByUserId(Long userId);
//	
//}
