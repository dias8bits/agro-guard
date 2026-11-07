package guard.agro.repository;

import guard.agro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean findUserByCpfIgnoreCase (String Cpf);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByCpfIgnoreCase(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);

}

