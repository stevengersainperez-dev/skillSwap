package mx.com.skill.swap.auth_service.serviceImpl;


import mx.com.skill.swap.auth_service.client.UserServiceClient;
import mx.com.skill.swap.auth_service.common.utils.TokenUtil;
import mx.com.skill.swap.auth_service.dto.*;
import mx.com.skill.swap.auth_service.entity.UserEntity;
import mx.com.skill.swap.auth_service.mapper.UserMapper;
import mx.com.skill.swap.auth_service.repository.UserRepository;
import mx.com.skill.swap.auth_service.security.JwtService;
import mx.com.skill.swap.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {
   private final UserRepository userRepository;
   private final UserMapper userMapper;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final UserServiceClient userServiceClient;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, UserServiceClient userServiceClient) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userServiceClient = userServiceClient;
    }


    @Transactional
    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        if(userRepository.findByEmail(registerDTO.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        UserEntity userEntity = userMapper.toEntity(registerDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("USER");
        userRepository.save(userEntity);

        CreateUserDTO createUserDTO = userMapper.toCreateUserDTO(userEntity);
        userServiceClient.createUser(createUserDTO);

        return userMapper.toUserDTO(userEntity);
    }

    @Transactional
    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if(!passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtService.generateToken(userEntity);
        return new LoginResponseDTO(token, userEntity.getEmail(), userEntity.getRole());
    }

    @Transactional
    @Override
    public void updateUserFromUserService(@RequestHeader("Authorization") String authHeader,
                                          UserProfileDTO userProfileDTO) {
        String token = TokenUtil.extractTokenFromHeader(authHeader);
        String email = jwtService.extractEmail(token);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.updateUserEntityFromUserProfileDTO(userProfileDTO, userEntity);
        userRepository.save(userEntity);
    }


}
