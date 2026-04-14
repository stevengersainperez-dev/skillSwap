package mx.com.skill.swap.auth_service.serviceImpl;


import mx.com.skill.swap.auth_service.dto.*;
import mx.com.skill.swap.auth_service.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public void updateUserFromUserService(String authHeader, UserProfileDTO userProfileDTO) {

    }
}
