package com.elearning.Enrollement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enrollment")
@PreAuthorize("hasRole('ROLE_USER')")
public class EnrollmentController {
	
	
	private EnrollmentService enrollmentService;
    private UserService userservice;
    private CourseService courseservice;
    
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, UserService userservice,
    		CourseService courseservice) {
		super();
		this.enrollmentService = enrollmentService;
		this.userservice = userservice;
		this.courseservice = courseservice;
	}

	

    @GetMapping("/save/{id_course}")
    public String saveEnrollment(@PathVariable Long id_course, Authentication authentication, Model model) {
        try {
            String username = authentication.getName();
            enrollmentService.createEnrollment(id_course, username);
            User user = userservice.findByUsername(username);
            Course course = courseservice.findById(id_course).get();
            model.addAttribute("course", course);
            model.addAttribute("user", user);
            return "enrollment-success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}