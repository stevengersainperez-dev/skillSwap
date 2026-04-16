package mx.com.skill.swep.user_service.controller;

import jakarta.validation.Valid;
import mx.com.skill.swep.user_service.dto.CreateUserDTO;
import mx.com.skill.swep.user_service.dto.UserProfileResponseDTO;
import mx.com.skill.swep.user_service.dto.UserProfileUpdateRequestDTO;
import mx.com.skill.swep.user_service.mapper.UserMapper;
import mx.com.skill.swep.user_service.repository.UserRepository;
import mx.com.skill.swep.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class  UserController {

    private final UserService userService;

    public UserController(UserService userService, UserMapper userMapper, UserRepository userRepository) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDTO> me(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(userService.me(authHeader));
    }

    @PostMapping("/create")
    public ResponseEntity<UserProfileResponseDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserProfileResponseDTO> updateUser(@RequestHeader("Authorization") String authHeader, @RequestBody @Valid UserProfileUpdateRequestDTO userProfileUpdateRequestDTO ){
        return ResponseEntity.ok(userService.updateUser(authHeader, userProfileUpdateRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponseDTO>> getUsers(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(userService.getAllUsers(authHeader));
    }

}
