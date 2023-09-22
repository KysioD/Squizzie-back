package fr.kysio.squeezie.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Integer idHistory;
    private LocalDateTime dateQuizz;
    private Float score;
    @JoinColumn(name = "id_quizz")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quizz quizz;
    @JoinColumn(name = "id_account")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}
