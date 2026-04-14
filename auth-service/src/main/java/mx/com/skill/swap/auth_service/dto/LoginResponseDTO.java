package mx.com.skill.swap.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDTO {

    private String token;
    private String email;
    private String role;

}
