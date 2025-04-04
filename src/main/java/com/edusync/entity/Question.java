package com.edusync.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne
    private Quiz quiz;

//    4 options in case of mcq questions
    private String option1;
    private String option2;
    private String option3;
    private String option4;


}
