package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Quizz;
import org.springframework.data.repository.CrudRepository;

public interface QuizzRepository extends CrudRepository<Quizz, Integer> {
}
