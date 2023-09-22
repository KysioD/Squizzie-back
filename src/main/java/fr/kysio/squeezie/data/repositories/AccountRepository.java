package fr.kysio.squeezie.data.repositories;

import fr.kysio.squeezie.data.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    @Query("FROM Account")
    List<Account> findAll();

    @Query("FROM Account WHERE username = :username")
    Optional<Account> findByUsername(@Param("username") final String username);
}
