package guard.agro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "the field 'email' is required")
    private String email;

    @NotBlank(message = "the field 'password' is required")
    private String password;
}