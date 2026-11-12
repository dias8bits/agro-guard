package guard.agro.dto.request;

import guard.agro.enums.OccurrenceGravityEnum;
import guard.agro.enums.OccurrenceStatusEnum;
import guard.agro.enums.OccurrenceTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OccurrencePostRequest {

    @NotBlank(message = "the field 'title' is required")
    String title;

    String description;

    @NotNull(message = "the field 'type' is required")
    OccurrenceTypeEnum type;

    @NotNull(message = "the field 'gravity' is required")
    OccurrenceGravityEnum gravity;

    @NotNull(message = "the field 'status' is required")
    OccurrenceStatusEnum status;

    @NotNull(message = "the field 'propertyId' is required")
    Long propertyId;
}
