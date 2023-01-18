package com.elearning.Enrollement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	@Query("SELECT e FROM Enrollment e WHERE e.course=:course")
	List<Enrollment> findAllByCourse(@Param("course")Course course);
	@Query("SELECT e FROM Enrollment e WHERE e.user=:user")
    List<Enrollment> findAllByUser(@Param("user")User user);
	@Query("SELECT e FROM Enrollment e WHERE e.course =:course and e.user=:user")
    Enrollment findByCourseAndUser(@Param("course") Course course,@Param("user") User user);
}
