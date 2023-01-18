package com.elearning.Teacher;

import java.util.List;

public interface ITeacherService {
	public void create(Teacher teacher);
	public List<Teacher> getAll();
	public void update(Teacher teacher);
	public void updateDetails(Teacher teacher);
	public void delete(Long id);

}
