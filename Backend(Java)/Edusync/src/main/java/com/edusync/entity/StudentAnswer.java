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
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student; // The student who answered

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private String student_response;

    private boolean isAutograded;

    @OneToOne(mappedBy = "studentAnswer", cascade = CascadeType.ALL)
    private Grading grading;

}














