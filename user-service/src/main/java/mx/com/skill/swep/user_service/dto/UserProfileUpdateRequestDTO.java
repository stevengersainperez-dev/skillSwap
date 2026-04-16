package mx.com.skill.swep.user_service.dto;

import lombok.Data;

@Data
public class UserProfileUpdateRequestDTO {

    private String name;
    private String lastName;
    private String secondLastName;
    private String address;
    private String phoneNumber;
    private String email;
}
