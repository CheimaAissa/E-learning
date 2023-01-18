package com.elearning.Teacher;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class TeacherService implements ITeacherService{
	
	
	private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void create(Teacher teacher) {
    	
    	if (null != teacherRepository.findByUsername(teacher.getUsername())) {
            throw new IllegalStateException("There is already a teacher with the name " + teacher.getUsername());
        } else if (null != teacherRepository.findByEmail(teacher.getEmailTeacher())) {
            throw new IllegalStateException("There is already a teacher with the email " + teacher.getEmailTeacher());
        }
        String username = teacher.getUsername();
        String password = new BCryptPasswordEncoder(11).encode(teacher.getPassword());
        String firstname = teacher.getFirstnameTeacher();
        String lastname = teacher.getLastnameteacher();
        String email = teacher.getEmailTeacher();
        String imgurl = teacher.getImgurl();
        String desc = teacher.getDescTeacher();
        LocalDate date = LocalDate.now();
        Teacher teach = new Teacher(username, password, firstname, lastname, email, imgurl, date, desc);
        teacherRepository.save(teach);
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void update(Teacher teacher) {
    	Teacher currentTeacher = teacherRepository.findById(teacher.getId_teacher()).get();

        currentTeacher.setFirstnameTeacher(teacher.getFirstnameTeacher());
        currentTeacher.setLastnameteacher(teacher.getLastnameteacher());
        currentTeacher.setEmailTeacher(teacher.getEmailTeacher());
        currentTeacher.setDescTeacher(teacher.getDescTeacher());
        currentTeacher.setImgurl(teacher.getImgurl());

        teacherRepository.save(currentTeacher);
    }

    
    public void updateDetails(Teacher teacher) {
    	Teacher current = teacherRepository.findById(teacher.getId_teacher()).get();

        current.setDetailTeacher(teacher.getDetailTeacher());

        teacherRepository.save(current);
    }

    public void delete(Long id) {
    	teacherRepository.deleteById(id);
    }

}
