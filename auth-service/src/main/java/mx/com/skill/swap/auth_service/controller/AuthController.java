package mx.com.skill.swap.auth_service.controller;


import jakarta.validation.Valid;
import mx.com.skill.swap.auth_service.dto.*;
import mx.com.skill.swap.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterDTO registerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody @Valid UserProfileDTO userProfileDTO){
        authService.updateUserFromUserService(authHeader, userProfileDTO);
        return ResponseEntity.ok().build();
    }



}
