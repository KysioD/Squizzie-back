package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Quizz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizzRepository extends CrudRepository<Quizz, Integer> {

    @Query("FROM Quizz q")
    List<Quizz> findAll();

}
