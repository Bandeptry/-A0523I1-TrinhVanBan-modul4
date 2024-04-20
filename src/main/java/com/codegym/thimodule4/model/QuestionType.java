package com.codegym.thimodule4.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "question_type")
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questionType")
    private Long idType;
    private String name;
    @OneToMany(mappedBy = "questionType")
    private List<QuestionContent> questionContents;

    public QuestionType() {
    }

    public QuestionType(Long idType, String name) {
        this.idType = idType;
        this.name = name;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionContent> getQuestionContents() {
        return questionContents;
    }

}
