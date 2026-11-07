package guard.agro.repository;

import guard.agro.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

    List<Occurrence> findByPropertyId(Long propertyId);
}
