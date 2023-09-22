package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Quizz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzRepository extends CrudRepository<Quizz, Integer> {

    @Query("FROM Quizz q")
    List<Quizz> findAll();

}
