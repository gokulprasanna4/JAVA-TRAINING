package com.ems.repo;
import com.ems.modal.InfoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InfoRequestRepo extends JpaRepository<InfoRequest, Long> {
    List<InfoRequest> findByUserId(Long userId);
}





















//package com.ems.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.ems.modal.InfoRequest;
//import java.util.List;
//
//
//public interface InfoRequestRepo extends JpaRepository<InfoRequest,Long>{
//	
//	List<InfoRequest> findByInfoRequestId(Long infoRequestId);
//	
//	List<InfoRequest> findByRequestType(String requestType);
//	List<InfoRequest> findByUserId(Long userId);
//	
//	
//	
//}
