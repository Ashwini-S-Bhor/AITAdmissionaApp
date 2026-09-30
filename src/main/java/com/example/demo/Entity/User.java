package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="AIT_USERS")
public class User {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer userId;
private String userName;
private String userEmail;
private Integer userPhno;
private String password;
private String Acc_Status;
@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
private List<Student> enquires;
}
