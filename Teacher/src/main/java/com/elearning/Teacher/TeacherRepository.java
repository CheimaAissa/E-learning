package com.elearning.Teacher;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	@Query("SELECT t FROM Teacher t WHERE t.username=:username")
	Teacher findByUsername(@Param("username")String username);  
	@Query("SELECT t FROM Teacher t WHERE t.email=:email")
	Teacher findByEmail(@Param("email")String email);
	@Query("SELECT t FROM Teacher t WHERE t.id_teacher=:id_teacher")
	Optional<Teacher> findById_teacher(@Param("id_teacher")Long id_teacher);
	@Modifying
	@Query("DELETE FROM Teacher t WHERE t.id_teacher=:id_teacher")
	void deleteById_teacher(@Param("id_teacher")Long id_teacher);
}
