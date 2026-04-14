package mx.com.skill.swap.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "You must provide a name")
    private String name;
    @NotBlank(message = "You must provide a last name")
    private String lastName;
    @NotBlank(message = "You must provide a second last name")
    private String secondLastName;
    @NotBlank(message = "You must provide a address")
    private String address;
    @NotBlank(message = "You must provide a phone number")
    private String phoneNumber;
    @Email(message = "You must provide a mail with a correct format")
    @NotBlank(message = "You must provide a email")
    private String email;
    @NotBlank(message = "You must provide a password")
    private String password;

}
