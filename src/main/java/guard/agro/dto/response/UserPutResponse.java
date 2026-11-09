package guard.agro.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPutResponse {

    @NotBlank(message = "the field 'name' is required")
    private String name;

    @Email(message = "invalid email")
    @NotBlank(message = "the field 'email' is required")
    private String email;

    @Pattern(
            regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "invalid cpf")
    @NotBlank(message = "the field 'cpf' is required")
    private String cpf;

}
