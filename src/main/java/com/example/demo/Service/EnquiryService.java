package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Student;
import com.example.demo.binding.DashboardResponse;
import com.example.demo.binding.EnquiryCriteria;
import com.example.demo.binding.EnquiryForm;

public interface EnquiryService {
public DashboardResponse getDashboardResponse (Integer UserId);
public String upsertEnquiry(EnquiryForm form);
public List<String> getCourseName();
public List<String> getEnquiryStatus();
public boolean saveEnquiry(EnquiryForm form);
public List<Student> getEnquries();
public List<Student>getFilteredEnqs(EnquiryCriteria criteria,Integer userId);
public Student getEnquiryById(Integer enquiryId);
}
