package mx.com.skill.swap.auth_service.service;


import mx.com.skill.swap.auth_service.dto.*;

public interface AuthService {


    UserDTO register(RegisterDTO registerDTO);

    LoginResponseDTO login(LoginDTO loginDTO);

    void updateUserFromUserService(String authHeader, UserProfileDTO userProfileDTO);

}
