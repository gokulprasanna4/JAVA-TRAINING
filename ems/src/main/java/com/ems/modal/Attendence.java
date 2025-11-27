package com.ems.modal;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendence_request") // Changed to underscore for better SQL compatibility
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;

	private Long userId;

	private Long reportingId;

	@Enumerated(EnumType.STRING)
	private RequestType attendenceType; // Updated field name

	private LocalDate startDate;
	private LocalDate endDate;

	@Column(nullable = true)
	private String userRequestComment;

	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	private String managerComment;

	public enum RequestType {
		ATTENDENCE, LEAVE
	}

	public enum Status {
		PENDING, APPROVED, REJECTED
	}
}