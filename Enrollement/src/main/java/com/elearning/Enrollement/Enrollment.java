package com.elearning.Enrollement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

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
@Data
class User{
	private long id;
	private String username;
	private String password;
}
@FeignClient(name="COURSE")
interface CourseService{
	@GetMapping("/course/{id}")
	Optional<Course> findById(@Param("course_id")Long id_Course);
}
@FeignClient(name="USER")
interface UserService{
	@GetMapping("/user/{id}")
	User findByUsername(@Param("username")String username);
}
@Entity
@Table(name = "enrollment")
public class Enrollment {
	@Id
    @Column(name = "enrollment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_enrollment;

    @Column(name = "date")
    private LocalDate enrollment_date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

	public Enrollment(LocalDate enrollment_date, User user, Course course) {
		super();
		this.enrollment_date = enrollment_date;
		this.user = user;
		this.course = course;
	}
	public Enrollment() {}

	public Long getId_enrollment() {
		return id_enrollment;
	}

	public void setId_enrollment(Long id_enrollment) {
		this.id_enrollment = id_enrollment;
	}

	public LocalDate getEnrollment_date() {
		return enrollment_date;
	}

	public void setEnrollment_date(LocalDate enrollment_date) {
		this.enrollment_date = enrollment_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

    

}