package guard.agro.dto.request;

import guard.agro.enums.EquipmentStatusEnum;
import guard.agro.enums.EquipmentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentPostRequest {

    @NotBlank(message = "the field 'title' is required")
    String name;

    @NotNull(message = "the field 'type' is required")
    EquipmentTypeEnum type;

    @NotNull(message = "the field 'status' is required")
    EquipmentStatusEnum status;

    @NotNull(message = "the field 'propertyId' is required")
    Long propertyId;
}
