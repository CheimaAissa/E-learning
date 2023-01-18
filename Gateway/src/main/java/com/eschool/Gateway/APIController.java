package com.eschool.Gateway;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Data
class Course{
	private Long id_Course;
    private String nameCourse;
    private String descriptionCourse;
    private String detailCourse;
    private String difficultyCourse;
    private String urlCourse;
    private String imgurl;
}
@FeignClient(name="COURSE")
interface CourseService{
	@GetMapping("/Teacher")
	public List<Course> getAll();
}

@Data
class Teacher{
	private Long id_teacher;
    private String username;
    private String password;
    private String firstnameTeacher;
    private String lastnameteacher;
    private String emailTeacher;
    private String descTeacher;
    private String detailTeacher;
    private LocalDate date;
    private String imgurl;
}
@FeignClient(name="TEACHER")
interface TeacherService{
	@GetMapping("/Teacher")
	public List<Teacher> getAll();
}
@RestController
@RequestMapping("/api")
public class APIController {
	
	
	private TeacherService teacherService;
	private CourseService courseService;

	 @Autowired
	 public APIController(TeacherService teacherService, CourseService courseService) {
	        super();
	        this.teacherService = teacherService;
	        this.courseService = courseService;
	    }

	    @GetMapping("/Teachers")
	    public List<Teacher> getAll() {
	        return this.teacherService.getAll();
	    }

	    @GetMapping("/course")
	    public List<Course> getAllCourse() {
	        return this.courseService.getAll();
	    }

}