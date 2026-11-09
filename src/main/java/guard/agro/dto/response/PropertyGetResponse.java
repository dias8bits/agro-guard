package guard.agro.dto.response;

import java.math.BigDecimal;
import java.util.Set;

public class PropertyGetResponse {

    Long id;

    String name;

    String address;

    BigDecimal hectares;

    Long ownerId;

    Set<Long> employeeIds;

}
