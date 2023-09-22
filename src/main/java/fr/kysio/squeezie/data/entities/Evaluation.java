package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "evaluation")
@Getter
@Setter
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Integer idEvaluation;
    private Integer stars;
    @JoinColumn(name = "id_quizz")
    @OneToOne(fetch = FetchType.LAZY)
    private Quizz quizz;
    @JoinColumn(name = "id_account")
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
