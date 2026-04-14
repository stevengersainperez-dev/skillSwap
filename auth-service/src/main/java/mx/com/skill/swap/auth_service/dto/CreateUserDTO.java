package mx.com.skill.swap.auth_service.dto;

import lombok.Data;

@Data
public class CreateUserDTO {

    private String name;
    private String lastName;
    private String secondLastName;
    private String address;
    private String phoneNumber;
    private String email;

}
