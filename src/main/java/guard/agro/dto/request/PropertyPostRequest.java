package guard.agro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PropertyPostRequest {

    @NotBlank(message = "the field 'name' is required")
    String name;

    @NotBlank(message = "the field 'address' is required")
    String address;

    @NotBlank(message = "the field 'hectares' is required")
    @Positive
    BigDecimal hectares;

    @NotBlank(message = "the field 'ownerId' is required")
    Long ownerId;

}
