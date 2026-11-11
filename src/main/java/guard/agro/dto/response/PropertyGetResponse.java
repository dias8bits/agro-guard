package guard.agro.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class PropertyGetResponse {

    Long id;

    String name;

    String address;

    BigDecimal hectares;

    Long ownerId;

    Set<Long> employeeIds;

}
