package com.elearning.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class CourseService {
    
	@Autowired
    private CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void create(Course course) throws Exception{
        if (null != courseRepository.findByNameCourse(course.getNameCourse())) {
            throw new Exception("A course with the name already exists " + course.getNameCourse());
        }
        Course cours = new Course(course);
        courseRepository.save(cours);
    }

    public void update(Course course, Long id_course) {
        Course currentCourse = courseRepository.findById(id_course).get();

            currentCourse.setNameCourse(course.getNameCourse());
            currentCourse.setDescriptionCourse(course.getDescriptionCourse());
            currentCourse.setDetailCourse(course.getDetailCourse());
            currentCourse.setDifficultyCourse(course.getDifficultyCourse());
            currentCourse.setUrlCourse(course.getUrlCourse());
            currentCourse.setImgurl(course.getImgurl());
            currentCourse.setTeacher(course.getTeacher());
            
            courseRepository.save(currentCourse);

    }

    public void delete(Course course) { 
    	courseRepository.delete(course); 
    }


    public List<Course> getAll() {
        return courseRepository.findAll();
    }


}
