package guard.agro.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PropertyPostResponse {

    Long id;

    String name;

    String address;

    BigDecimal hectares;

    Long ownerId;

}
