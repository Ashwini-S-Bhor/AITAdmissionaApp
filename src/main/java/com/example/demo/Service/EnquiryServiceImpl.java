package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Courses;
import com.example.demo.Entity.EnquiryStatus;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.StatusRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.binding.DashboardResponse;
import com.example.demo.binding.EnquiryCriteria;
import com.example.demo.binding.EnquiryForm;

import jakarta.servlet.http.HttpSession;
@Service
public class EnquiryServiceImpl implements EnquiryService{
@Autowired
private UserRepository repo;
@Autowired
private CourseRepository courseRepo;
@Autowired
private StatusRepository statusRepo;
@Autowired
private StudentRepository studentRepo;
@Autowired
private HttpSession session;
DashboardResponse response=new DashboardResponse();
	@Override
	public DashboardResponse getDashboardResponse(Integer UserId) {
		Optional<User>findById=repo.findById(UserId);
		if(findById.isPresent()) {
			User u1=findById.get();
			List<Student>enquiries=u1.getEnquires();
			Integer totalCnt=enquiries.size();
			Integer enrolledCnt=enquiries.stream()
			.filter(e->e.getEnquiryStatus().equals("ENROLLED"))
		.collect(Collectors.toList()).size();
			Integer lostCnt=enquiries.stream()
					.filter(e->e.getEnquiryStatus().equals("LOST"))
					.collect(Collectors.toList()).size();
			response.setTotalEnqCount(totalCnt);
			response.setEnrolledCount(enrolledCnt);
			response.setLostCount(lostCnt);			
			
			
		}
		
		return response;
	}
	@Override
	public List<String> getCourseName() {
		List<Courses> findAll=courseRepo.findAll();
		List<String> names=new ArrayList<>();
		
		for(Courses entity:findAll) {
			names.add(entity.getCourseName());
		}
		return names;
	}
	@Override
	public List<String> getEnquiryStatus() {
		List<EnquiryStatus>findAll=statusRepo.findAll();
		List<String> statusList=new ArrayList<>();
		for(EnquiryStatus entity:findAll) {
			statusList.add(entity.getEnquiryStatus());
		}
		return statusList;
	}
	@Override
	public boolean saveEnquiry(EnquiryForm form) {

	    Student student;

	    if (form.getEnquiryId() != null) {

	         student = studentRepo.findById(form.getEnquiryId()).get();
	        
	       

	    } else {

	        student = new Student();

	        Integer userId = (Integer) session.getAttribute("userId");

	        User user = repo.findById(userId).get();

	        student.setUser(user);
	    }

	    BeanUtils.copyProperties(form, student);

	    studentRepo.save(student);

	    return true;
	}
	
	@Override
	public String upsertEnquiry(EnquiryForm form) {
		return null;
	}

	@Override
	public List<Student> getEnquries() {
		Integer userId=(Integer)session.getAttribute("userId");
		Optional<User>user=repo.findById(userId);
		if(user.isPresent()) {
			User user1=user.get();
			List<Student>student=user1.getEnquires();
			return student;
		}
		return null;
	}
	@Override
	public List<Student> getFilteredEnqs(EnquiryCriteria criteria, Integer userId) {
		Optional<User>user=repo.findById(userId);
		if(user.isPresent()) {
			User user1=user.get();
			List<Student>student=user1.getEnquires();
			
		if(null!=criteria.getCourseName()&& !"".equals(criteria.getCourseName())) {
				student=student.stream()
				.filter(e->e.getCourseName().equals(criteria.getCourseName()))
				.collect(Collectors.toList());
			}
		if(null!=criteria.getEnquiryStatus()&&!"".equals(criteria.getEnquiryStatus())) {
			student=student.stream()
					.filter(e->e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
					.collect(Collectors.toList());}
		if(null!=criteria.getClassMode()&& !"".equals(criteria.getClassMode())) {
			student=student.stream()
					.filter(e->e.getClassMode().equals(criteria.getClassMode()))
					.collect(Collectors.toList());
				
		}
			
			
		
		
			return student;
		}
		
		return  new ArrayList<>();
	}
	@Override
	public Student getEnquiryById(Integer enquiryId) {
		Optional<Student>student=studentRepo.findById(enquiryId);
		if(student.isPresent()) {
			return student.get();
		}
		
		return null;
	}

	

	

	

	

}
