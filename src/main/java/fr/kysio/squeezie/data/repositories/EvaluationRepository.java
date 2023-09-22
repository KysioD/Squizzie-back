package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Evaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends CrudRepository<Evaluation, Integer> {

    @Query("FROM Evaluation e WHERE e.quizz.idQuizz = :quizzId")
    List<Evaluation> findAll(@Param("quizzId") final Integer quizzId);

    @Query("FROM Evaluation e WHERE e.account.username = :username")
    List<Evaluation> findAll(@Param("username") final String username);

}
