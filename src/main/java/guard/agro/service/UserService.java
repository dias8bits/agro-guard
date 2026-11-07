package guard.agro.service;

import guard.agro.Exception.CpfAlreadyExistsException;
import guard.agro.Exception.EmailAlreadyExistsException;
import guard.agro.Exception.NotFoundException;
import guard.agro.model.User;
import guard.agro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(Long id) {
        return id == null ? userRepository.findAll() : Collections.singletonList(findUserByIdOrElseThrow(id));
    }

    public User findUserByIdOrElseThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("user with id %d not found", id)
                ));
    }

    @Transactional
    public User save (User userToSave) {
        validateUniqueFields(userToSave);

        return userRepository.save(userToSave);
    }

    @Transactional
    public User update (User userToUpdate, Long id) {
        findUserByIdOrElseThrow(id);
        userToUpdate.setId(id);

        validateUniqueFieldsForUpdate(userToUpdate);

        return userRepository.save(userToUpdate);
    }

    @Transactional
    public void delete (User user) {
        var userToDelete = findUserByIdOrElseThrow(user.getId());

        userRepository.delete(userToDelete);
    }

    private void validateUniqueFields(User user) {
        assertThatEmailDoesNotExistOrElseThrow(user.getEmail());
        assertThatCpfDoesNotExistOrElseThrow(user.getCpf());
    }

    private void validateUniqueFieldsForUpdate(User user) {
        assertThatEmailDoesNotExistOrElseThrow(user.getEmail(), user.getId());
        assertThatCpfDoesNotExistOrElseThrow(user.getCpf(), user.getId());
    }

    private void assertThatEmailDoesNotExistOrElseThrow(String email) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new EmailAlreadyExistsException(
                    String.format("user with email '%s' already exists", email)
            );
        }
    }

    private void assertThatEmailDoesNotExistOrElseThrow(String email, Long id) {
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailAlreadyExistsException(
                    String.format("user with email '%s' already exists", email)
            );
        }
    }

    private void assertThatCpfDoesNotExistOrElseThrow(String cpf) {
        if (userRepository.existsByCpfIgnoreCase(cpf)) {
            throw new CpfAlreadyExistsException(
                    String.format("user with cpf '%s' already exists", cpf)
            );
        }
    }

    private void assertThatCpfDoesNotExistOrElseThrow(String cpf, Long id) {
        if (userRepository.existsByCpfAndIdNot(cpf, id)) {
            throw new CpfAlreadyExistsException(
                    String.format("user with cpf '%s' already exists", cpf)
            );
        }
    }

}
