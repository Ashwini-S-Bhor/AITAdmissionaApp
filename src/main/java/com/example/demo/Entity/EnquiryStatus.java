package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="AIT_ENQUIRY_STATUS")

@Data
public class EnquiryStatus {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    
	private Integer enquiryId;
private String enquiryStatus;
}
