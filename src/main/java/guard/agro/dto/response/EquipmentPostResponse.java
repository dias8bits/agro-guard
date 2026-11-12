package guard.agro.dto.response;

import guard.agro.enums.EquipmentStatusEnum;
import guard.agro.enums.EquipmentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentPostResponse {

    Long id;

    String name;

    EquipmentTypeEnum type;

    EquipmentStatusEnum status;

    Long propertyId;
}
