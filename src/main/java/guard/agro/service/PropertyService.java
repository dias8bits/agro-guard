package guard.agro.service;

import guard.agro.exception.NotFoundException;
import guard.agro.model.Property;
import guard.agro.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public List<Property> findAll(Long id) {
        return id == null ? propertyRepository.findAll() : Collections.singletonList(findPropertyByIdOrElseThrow(id));
    }

    public Property findPropertyByIdOrElseThrow(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("property with id %d not found", id)
                ));
    }

    @Transactional
    public Property save(Property propertyToSave) {
        resolveOwner(propertyToSave);

        return propertyRepository.save(propertyToSave);
    }

    @Transactional
    public Property update(Property propertyToUpdate, Long id) {
        var existing = findPropertyByIdOrElseThrow(id);
        propertyToUpdate.setId(id);
        propertyToUpdate.setEmployees(existing.getEmployees());

        resolveOwner(propertyToUpdate);

        return propertyRepository.save(propertyToUpdate);
    }

    @Transactional
    public void delete(Property property) {
        var propertyToDelete = findPropertyByIdOrElseThrow(property.getId());

        propertyRepository.delete(propertyToDelete);
    }

    @Transactional
    public Property addEmployee(Long propertyId, Long userId) {
        var property = findPropertyByIdOrElseThrow(propertyId);
        var user = userService.findUserByIdOrElseThrow(userId);

        property.getEmployees().add(user);

        return property;
    }

    @Transactional
    public Property removeEmployee(Long propertyId, Long userId) {
        var property = findPropertyByIdOrElseThrow(propertyId);
        var user = userService.findUserByIdOrElseThrow(userId);

        property.getEmployees().remove(user);

        return property;
    }

    private void resolveOwner(Property property) {
        if (property.getOwner() == null || property.getOwner().getId() == null) {
            throw new NotFoundException("property owner must be informed");
        }

        var owner = userService.findUserByIdOrElseThrow(property.getOwner().getId());
        property.setOwner(owner);
    }

}