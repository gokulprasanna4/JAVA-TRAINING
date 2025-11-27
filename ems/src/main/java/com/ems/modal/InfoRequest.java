package com.ems.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long infoRequestId;
	
	@Column(nullable = false)
	private Long userId;

	@Column(length = 50)
	private String requestType;
	
	@Column(length = 200)
	private String requestDescription;
	
	@Enumerated(EnumType.STRING)
    private Status status;

	public enum Status {
		RESOLVED, SUBMITTED
	}
}
