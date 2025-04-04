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
public class Grading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private StudentAnswer studentAnswer;

    private Integer score;
    private String feedback;
    private boolean isAutograded;


}
