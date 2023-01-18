package com.elearning.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	@Query("SELECT c FROM Course c WHERE c.nameCourse=:nameCourse")
	Course findByNameCourse(@Param("nameCourse")String nameCourse);
	@Query("SELECT c FROM Course c WHERE c.teacher=:teacher")
    List<Course> findAllByTeacher(@Param("teacher")Teacher teacher);
	@Query("SELECT t FROM Teacher t WHERE t.course_id=:course_id")
	Optional<Course> findById(@Param("course_id")Long id_Course);

}