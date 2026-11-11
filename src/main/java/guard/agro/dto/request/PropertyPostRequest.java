package guard.agro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PropertyPostRequest {

    @NotBlank(message = "the field 'name' is required")
    String name;

    @NotBlank(message = "the field 'address' is required")
    String address;

    @NotNull(message = "the field 'hectares' is required")
    @Positive
    BigDecimal hectares;

    @NotNull(message = "the field 'ownerId' is required")
    Long ownerId;

}
