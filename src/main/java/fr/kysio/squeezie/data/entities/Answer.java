package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private Integer idAnswer;
    private String name;
    private boolean correct;
    @JoinColumn(name = "id_question")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

}
