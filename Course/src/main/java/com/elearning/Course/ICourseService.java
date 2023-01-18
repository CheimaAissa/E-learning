package com.elearning.Course;

import java.util.List;

public interface ICourseService {
	public void create(Course course);
	public void update(Course course, Long id_course);
	public void delete(Course course);
	public List<Course> getAll();

}
