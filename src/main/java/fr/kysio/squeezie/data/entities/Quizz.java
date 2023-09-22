package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quizz")
@Getter
@Setter
public class Quizz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quizz")
    private Integer idQuizz;
    private String title;
    private String description;
    @JoinColumn(name = "id_account")
    @OneToOne(fetch = FetchType.LAZY)
    private Account author;

}
