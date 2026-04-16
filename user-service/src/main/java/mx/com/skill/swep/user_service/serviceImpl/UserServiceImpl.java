package mx.com.skill.swep.user_service.serviceImpl;


import mx.com.skill.swep.user_service.client.AuthServiceClient;
import mx.com.skill.swep.user_service.dto.CreateUserDTO;
import mx.com.skill.swep.user_service.dto.UserProfileResponseDTO;
import mx.com.skill.swep.user_service.dto.UserProfileUpdateRequestDTO;
import mx.com.skill.swep.user_service.entity.UserEntity;
import mx.com.skill.swep.user_service.mapper.UserMapper;
import mx.com.skill.swep.user_service.repository.UserRepository;
import mx.com.skill.swep.user_service.security.JwtService;
import mx.com.skill.swep.user_service.service.UserService;
import mx.com.skill.swep.user_service.utils.TokenUtil;
import mx.com.skill.swep.user_service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final AuthServiceClient authServiceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, UserMapper userMapper,
                           AuthServiceClient authServiceClient) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.authServiceClient = authServiceClient;
    }

    @Override
    public List<UserProfileResponseDTO> getAllUsers(String authHeader) {
        String token = TokenUtil.extractTokenFromHeader(authHeader);
        String role = jwtService.extractRole(token);
        if(!role.equals("ADMIN")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied");
        }

        List<UserEntity> userEntities = userRepository.findAll();

        return userMapper.fromUserEntityListToUserProfileResponseDTOList(userEntities);
    }

    @Override
    public UserProfileResponseDTO me(String authHeader) {
        String token = TokenUtil.extractTokenFromHeader(authHeader);
        String email = jwtService.extractEmail(token);

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        return userMapper.fromUserEntitytoUserProfileResponseDTO(userEntity);
    }

    @Transactional
    @Override
    public UserProfileResponseDTO createUser(CreateUserDTO createUserDTO) {
        UserEntity userEntity = userMapper.fromCreateUserDTOtoUserEntity(createUserDTO);
        userRepository.save(userEntity);
        return userMapper.fromUserEntitytoUserProfileResponseDTO(userEntity);
    }

    @Transactional
    @Override
    public UserProfileResponseDTO updateUser(String authHeader, UserProfileUpdateRequestDTO userProfileUpdateRequestDTO) {
        Validation.requestObjectShouldBeNotNull(userProfileUpdateRequestDTO, "userProfileUpdateRequestDTO");

        String token = TokenUtil.extractTokenFromHeader(authHeader);
        String email =  jwtService.extractEmail(token);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.updateUserEntityFromUserProfileUpdateDTO(userProfileUpdateRequestDTO, userEntity);
        UserEntity saved = userRepository.save(userEntity);

        authServiceClient.updateUser(authHeader, userProfileUpdateRequestDTO);
        return userMapper.fromUserEntitytoUserProfileResponseDTO(saved);
    }
}
