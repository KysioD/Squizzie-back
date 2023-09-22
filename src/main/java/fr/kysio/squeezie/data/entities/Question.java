package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Integer idQuestion;
    private String title;
    private String description;
    private Boolean correct;
    @JoinColumn(name = "id_quizz")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quizz quizz;
}
