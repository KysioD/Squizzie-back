package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends CrudRepository<Answer, Integer> {

    @Query("SELECT a FROM Answer a WHERE a.account.username = :username and a.question.quizz.idQuizz = :quizzId")
    List<Answer> findAllByUsernameAndQuizz(@Param("username") final String username, @Param("quizzId") final int quizzId);

}
