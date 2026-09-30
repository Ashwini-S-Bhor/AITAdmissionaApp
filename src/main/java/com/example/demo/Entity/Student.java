package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="STUDENTS")
@Data
public class Student {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer enquiryId;
private String studentName;
private Integer studentPhno;
private String classMode;
private String courseName;
private String enquiryStatus;
@CreationTimestamp
private LocalDate createDate;
@UpdateTimestamp
private LocalDate updatedDate;
@ManyToOne
@JoinColumn(name="userId")
private User user;

}
