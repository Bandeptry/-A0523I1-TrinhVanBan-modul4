package com.codegym.thimodule4.repository;

import com.codegym.thimodule4.model.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionContentRepository extends JpaRepository<QuestionContent, Integer> {
    @Query(value = "select * " +
            "from question_content qs where title like :title", nativeQuery = true)
    Page<QuestionContent> fillAllAndSearchByName(@Param("title")String questionContent, Pageable pageable);
    @Query(value = "SELECT  qs.id_question_type, qs.id_content, qs.answer, qs.title, qs.content, qs.date_create, qs.status  FROM questioncontent.question_content qs\n" +
            "INNER JOIN questioncontent.question_type qt ON qs.id_question_type = qt.id_question_type\n" +
            "WHERE qs.title LIKE :title AND qt.name LIKE :selectQuestion\n", nativeQuery = true)
    Page<QuestionContent> fillAllSearchByNameTitle(@Param("title")String questionContent,@Param("selectQuestion")String selectQuestion, Pageable pageable);
}
