package com.gl.capstoneproject.doconnect.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.capstoneproject.doconnect.Entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
	
	List<Question> findByQuestionIgnoreCaseContaining(String searchText);
	
	@Query(nativeQuery = true, value="select question from questions where id = (:questionId)")
	String findQuestionById(@Param("questionId") String id);
	
	@Query(nativeQuery = true, value="select * from questions where approval = false")
	List<Question> findAllUnapprovedQuestions();
	
	@Query(nativeQuery = true, value="select * from questions where id = (:questionId) and approval = true")
	Question findByQuestionId(@Param("questionId") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update questions set approval = true where id = (:questionId)")
	void updateAsApproved(@Param("questionId") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="delete from questions where id = (:questionId)")
	void deleteQuestion(@Param("questionId") String id);
	@Query(nativeQuery=true,value="select * from questions where approval=true")
	List<Question> findAllApprovedQuestions();

}