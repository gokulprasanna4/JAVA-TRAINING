package com.ems.repo;
import com.ems.modal.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendenceRepo extends JpaRepository<Attendence, Long> {
    List<Attendence> findByUserId(Long userId);
}

















//package com.ems.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.ems.modal.Attendence;
//import com.ems.modal.Attendence.Status;
//
//import java.util.List;
//
//public interface AttendenceRepo extends JpaRepository<Attendence, Long> {
//	
//	
//	List<Attendence> findByRequestId(Long requestId);
//
//	List<Attendence> findByUserId(Long userId);
//
//	List<Attendence> findByReportingId(Long reportingId);
//	
//	List<Attendence> findByAttendenceCategory(String attendenceCategory);
//	
//	List<Attendence> findByStatus(Status status);
//	
//
//}
