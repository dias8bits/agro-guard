package guard.agro.service;

import guard.agro.exception.NotFoundException;
import guard.agro.model.Occurrence;
import guard.agro.repository.OccurrenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OccurrenceService {

    private final OccurrenceRepository occurrenceRepository;
    private final PropertyService propertyService;

    public List<Occurrence> findAll(Long id) {
        return id == null ? occurrenceRepository.findAll() : Collections.singletonList(findOccurrenceByIdOrElseThrow(id));
    }

    public List<Occurrence> findAllByPropertyId(Long propertyId) {
        propertyService.findPropertyByIdOrElseThrow(propertyId);

        return occurrenceRepository.findByPropertyId(propertyId);
    }

    public Occurrence findOccurrenceByIdOrElseThrow(Long id) {
        return occurrenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("occurrence with id %d not found", id)
                ));
    }

    @Transactional
    public Occurrence save(Occurrence occurrenceToSave) {
        resolveProperty(occurrenceToSave);

        return occurrenceRepository.save(occurrenceToSave);
    }

    @Transactional
    public Occurrence update(Occurrence occurrenceToUpdate, Long id) {
        var existing = findOccurrenceByIdOrElseThrow(id);
        occurrenceToUpdate.setId(id);
        occurrenceToUpdate.setDateTime(existing.getDateTime());

        resolveProperty(occurrenceToUpdate);

        return occurrenceRepository.save(occurrenceToUpdate);
    }

    @Transactional
    public void delete(Occurrence occurrence) {
        var occurrenceToDelete = findOccurrenceByIdOrElseThrow(occurrence.getId());

        occurrenceRepository.delete(occurrenceToDelete);
    }

    private void resolveProperty(Occurrence occurrence) {
        if (occurrence.getProperty() == null || occurrence.getProperty().getId() == null) {
            throw new NotFoundException("occurrence property must be informed");
        }

        var property = propertyService.findPropertyByIdOrElseThrow(occurrence.getProperty().getId());
        occurrence.setProperty(property);
    }

}