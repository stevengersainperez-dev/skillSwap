package mx.com.skill.swep.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminUserUpdateDTO {

    @NotBlank(message = "You must provide a name")
    private String name;
    @NotBlank(message = "You must provide a last name")
    private String lastName;
    @NotBlank(message = "You must provide a second last name")
    private String secondLastName;
    @NotBlank(message = "You must provide an address")
    private String address;
    @NotBlank(message = "You must privide a phone number")
    private String phoneNumber;
    @NotBlank(message = "You must provide an email")
    private String email;
    @NotBlank(message = "You must provide a password")
    private String password;
    @NotBlank(message = "You must provide a role")
    private String role;

}
