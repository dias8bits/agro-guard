package guard.agro.dto.response;

import guard.agro.enums.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostResponse {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    private UserRoleEnum role;
}
