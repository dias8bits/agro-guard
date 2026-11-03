package guard.agro.model;

import guard.agro.enums.EquipmentStatusEnum;
import guard.agro.enums.EquipmentTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentTypeEnum type;

    @Enumerated(EnumType.STRING)
    private EquipmentStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
