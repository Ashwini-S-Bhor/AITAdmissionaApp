package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Student;
import com.example.demo.Service.EnquiryService;
import com.example.demo.binding.DashboardResponse;
import com.example.demo.binding.EnquiryCriteria;
import com.example.demo.binding.EnquiryForm;

import jakarta.servlet.http.HttpSession;
@Controller
public class EnquiryController {
	@Autowired
	private HttpSession session;
	@Autowired
	private EnquiryService enqService;
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	@GetMapping("/add")
	public String AddPage(Model model)
	{
		List<String>courses=enqService.getCourseName();
		List<String>status=enqService.getEnquiryStatus();
		EnquiryForm form=new EnquiryForm();
		model.addAttribute("courseName",courses);
		model.addAttribute("enquiryStatus",status);
		model.addAttribute("enquiry",form);
		return "add";
	}
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Integer userId=(Integer)session.getAttribute("userId");
		DashboardResponse response=enqService.getDashboardResponse(userId);
		model.addAttribute("response",response);
		
		return"dashboard";
	}
	@PostMapping("/add")
	public String AddPage(@ModelAttribute("enquiry")EnquiryForm form,Model model) {
		boolean status=enqService.saveEnquiry(form);
		if(status) {
			model.addAttribute("succMsg","Enquiry Added");
		}else {
			model.addAttribute("errMsg","Problem occured");
		}
		return "add";
	}
	private void initForm(Model model) {
		List<String>courses=enqService.getCourseName();
		List<String>enqStatus=enqService.getEnquiryStatus();
		EnquiryForm form=new EnquiryForm();
		model.addAttribute("coursesNames",courses);
		model.addAttribute("statusNames",enqStatus);
		model.addAttribute("form",form);
	}
	@GetMapping("/view")
	public String viewPage(Model model) {
		initForm(model);
		List<Student>student=enqService.getEnquries();
		model.addAttribute("student",student);
		return"view";
	}
	@GetMapping("/filter-enquiries")
	public String getFilteredEnq(@RequestParam String cname,@RequestParam String status,@RequestParam String mode,Model model) {
	EnquiryCriteria form=new EnquiryCriteria();
	form.setCourseName(cname);
	form.setClassMode(mode);
	form.setEnquiryStatus(status);
	Integer userId=(Integer)session.getAttribute("userId");
		List<Student>filterdEnqs=enqService.getFilteredEnqs(form,userId);
		model.addAttribute("student",filterdEnqs);
		return"filter-enquiry-table";
	}
@GetMapping("/edit")
public String getEditPage(@RequestParam Integer enquiryId,Model model  ) {
	Student student=enqService.getEnquiryById(enquiryId);
	EnquiryForm form=new EnquiryForm();
	BeanUtils.copyProperties(student, form);
	model.addAttribute("enquiry",form);
	model.addAttribute("courseName",enqService.getCourseName());
	model.addAttribute("enquiryStatus",enqService.getEnquiryStatus());
	return "add";
}

}
