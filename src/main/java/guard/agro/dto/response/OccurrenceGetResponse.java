package guard.agro.dto.response;

import guard.agro.enums.OccurrenceGravityEnum;
import guard.agro.enums.OccurrenceStatusEnum;
import guard.agro.enums.OccurrenceTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OccurrenceGetResponse {

    String title;

    String description;

    OccurrenceTypeEnum type;

    OccurrenceGravityEnum gravity;

    OccurrenceStatusEnum status;

    LocalDateTime dateTime;

    Long propertyId;
}
