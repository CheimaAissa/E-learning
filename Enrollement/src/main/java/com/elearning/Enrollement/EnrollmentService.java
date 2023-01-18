package com.elearning.Enrollement;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
	
	 private EnrollmentRepository enrollmentRepository;
	 private CourseService courseservice;
	 private UserService userservice;

	    @Autowired
	    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseService courseservice, UserService userservice) {
	        this.enrollmentRepository = enrollmentRepository;
	        this.courseservice = courseservice;
	        this.userservice = userservice;
	    }

	    public void createEnrollment(Long id_Course, String username) throws Exception {
	        Course course = courseservice.findById(id_Course).get();
	        User user = userservice.findByUsername(username);

	        if (null != enrollmentRepository.findByCourseAndUser(course, user)) {
	            throw new Exception("You are already enrolled in this course");
	        }
	        LocalDate date = LocalDate.now();
	        Enrollment enrollment = new Enrollment(date, user, course);
	        enrollmentRepository.save(enrollment);
	    }

}