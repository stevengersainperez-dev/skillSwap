package mx.com.skill.swap.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmpty(message = "You must provide a email")
    private String email;
    @NotBlank(message = "You must provide a password")
    private String password;

}
