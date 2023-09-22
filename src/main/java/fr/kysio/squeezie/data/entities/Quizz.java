package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quizz_question",
            joinColumns = @JoinColumn(name = "id_quizz"),
            inverseJoinColumns = @JoinColumn(name = "id_question"))
    private List<Question> questions;

}
