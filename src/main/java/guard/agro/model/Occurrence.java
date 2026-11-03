package guard.agro.model;

import guard.agro.enums.OccurrenceGravityEnum;
import guard.agro.enums.OccurrenceStatusEnum;
import guard.agro.enums.OccurrenceTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "occurrence")
public class Occurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private OccurrenceTypeEnum type;

    @Enumerated(EnumType.STRING)
    private OccurrenceGravityEnum gravity;

    @Enumerated(EnumType.STRING)
    private OccurrenceStatusEnum status;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
}
