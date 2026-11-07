package guard.agro.service;

import guard.agro.Exception.NotFoundException;
import guard.agro.model.Equipment;
import guard.agro.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final PropertyService propertyService;

    public List<Equipment> findAll(Long id) {
        return id == null ? equipmentRepository.findAll() : Collections.singletonList(findEquipmentByIdOrElseThrow(id));
    }

    public List<Equipment> findAllByPropertyId(Long propertyId) {
        propertyService.findPropertyByIdOrElseThrow(propertyId);

        return equipmentRepository.findByPropertyId(propertyId);
    }

    public Equipment findEquipmentByIdOrElseThrow(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("equipment with id %d not found", id)
                ));
    }

    @Transactional
    public Equipment save(Equipment equipmentToSave) {
        resolveProperty(equipmentToSave);

        return equipmentRepository.save(equipmentToSave);
    }

    @Transactional
    public Equipment update(Equipment equipmentToUpdate, Long id) {
        findEquipmentByIdOrElseThrow(id);
        equipmentToUpdate.setId(id);

        resolveProperty(equipmentToUpdate);

        return equipmentRepository.save(equipmentToUpdate);
    }

    @Transactional
    public void delete(Equipment equipment) {
        var equipmentToDelete = findEquipmentByIdOrElseThrow(equipment.getId());

        equipmentRepository.delete(equipmentToDelete);
    }

    private void resolveProperty(Equipment equipment) {
        if (equipment.getProperty() == null || equipment.getProperty().getId() == null) {
            throw new NotFoundException("equipment property must be informed");
        }

        var property = propertyService.findPropertyByIdOrElseThrow(equipment.getProperty().getId());
        equipment.setProperty(property);
    }

}