package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    @Query("SELECT h FROM History h WHERE h.account.username = :username")
    List<History> findAllByAccountName(@Param("username") final String username);

    @Query("SELECT h FROM History h WHERE h.quizz.idQuizz = :quizzId")
    List<History> findAllByQuizzId(@Param("quizzId") final int quizzId);
}
