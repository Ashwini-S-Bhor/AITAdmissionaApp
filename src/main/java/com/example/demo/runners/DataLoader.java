package com.example.demo.runners;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Courses;
import com.example.demo.Entity.EnquiryStatus;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.StatusRepository;

@Component
public class DataLoader implements ApplicationRunner{
	@Autowired
private CourseRepository courseRepo;
	@Autowired
	private StatusRepository statusRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		courseRepo.deleteAll();
		statusRepo.deleteAll();
		Courses c1=new Courses();
		c1.setCourseName("Java");
		Courses c2=new Courses();
		c2.setCourseName("Python");
		Courses c3=new Courses();
		c3.setCourseName("DevOps");
         Courses c4=new Courses();
         c4.setCourseName("AWS");
         courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
         EnquiryStatus e1=new EnquiryStatus();
         e1.setEnquiryStatus("NEW");
         EnquiryStatus e2=new EnquiryStatus();
         e2.setEnquiryStatus("ENROLLED");
         EnquiryStatus e3=new EnquiryStatus();
         e3.setEnquiryStatus("LOST");
         statusRepo.saveAll(Arrays.asList(e1,e2,e3));
	}

}
