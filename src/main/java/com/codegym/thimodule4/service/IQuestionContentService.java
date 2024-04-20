package com.codegym.thimodule4.service;

import com.codegym.thimodule4.model.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQuestionContentService {
    List<QuestionContent> showListContent();
    void create(QuestionContent content);
    void removeById(Integer id);
    QuestionContent detail(Integer id);
    Page<QuestionContent> findAllAndSearch(String title, Pageable pageable);
    Page<QuestionContent> findAllSearchTitle(String title, String selectQuestion, Pageable pageable);
}
