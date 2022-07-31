package com.gl.capstoneproject.doconnect.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.capstoneproject.doconnect.Entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
	
	@Query(nativeQuery = true, value = "select * from answers where approval = false")
	List<Answer> findAllUnapprovedAnswers();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update answers set approval = true where id = (:answerId);")
	void updateAsApproved(@Param("answerId") String answerId);
	
	@Query(nativeQuery = true, value = "select * from answers where id = (:answerId)")
	Answer findByAnswerId(@Param("answerId") String answerId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from answers where id = (:answerId)")
	void deleteAnswer(@Param("answerId") String answerId);
	
	@Query(nativeQuery = true, value = "select * from answers where approval = true")
	List<Answer> findAllApprovedAnswers();
	
	@Query(nativeQuery = true, value = "select * from answers where question_id = (:questionId)")
	List<Answer> findAnswersByQuestionId(@Param("questionId") String questionId);
}
