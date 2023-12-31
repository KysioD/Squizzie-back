package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query("FROM Question q WHERE q.quizz.idQuizz = :idQuizz")
    List<Question> findAllByQuizzId(@Param("idQuizz") final int idQuizz);
}
