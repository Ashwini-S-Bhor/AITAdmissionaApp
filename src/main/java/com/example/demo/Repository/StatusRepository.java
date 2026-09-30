package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.EnquiryStatus;

public interface StatusRepository extends JpaRepository<EnquiryStatus,Integer> {

}
