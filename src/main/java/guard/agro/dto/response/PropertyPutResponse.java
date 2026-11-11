package guard.agro.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
public class PropertyPutResponse {

    Long id;

    String name;

    String address;

    BigDecimal hectares;

    Long ownerId;

}
