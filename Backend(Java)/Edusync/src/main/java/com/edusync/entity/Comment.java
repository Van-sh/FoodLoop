package com.edusync.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String textComment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Discussion discussion;

    @CreatedDate
    private LocalDateTime timestamp;

    private Integer upvotes;
}
