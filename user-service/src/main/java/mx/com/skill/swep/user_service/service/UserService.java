package mx.com.skill.swep.user_service.service;



import mx.com.skill.swep.user_service.dto.CreateUserDTO;
import mx.com.skill.swep.user_service.dto.UserProfileResponseDTO;
import mx.com.skill.swep.user_service.dto.UserProfileUpdateRequestDTO;

import java.util.List;

public interface UserService {

    UserProfileResponseDTO me(String authHeader);

    UserProfileResponseDTO createUser(CreateUserDTO createUserDTO);

    UserProfileResponseDTO updateUser(String authHeader, UserProfileUpdateRequestDTO userProfileUpdateRequestDTO);

    List<UserProfileResponseDTO> getAllUsers(String authHeader);

}
