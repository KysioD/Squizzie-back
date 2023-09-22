package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "id_quizz")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quizz quizz;

}
