package guard.agro.repository;

import guard.agro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByCpfIgnoreCase(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);

}