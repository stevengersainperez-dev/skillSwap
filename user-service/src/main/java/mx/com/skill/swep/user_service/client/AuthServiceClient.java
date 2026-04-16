package mx.com.skill.swep.user_service.client;

import mx.com.skill.swep.user_service.dto.UserProfileUpdateRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url ="http://localhost:8001")
public interface AuthServiceClient {

    @PutMapping("/auth/update-user")
    void updateUser(@RequestHeader("Authorization") String token, @RequestBody UserProfileUpdateRequestDTO userProfileUpdateRequestDTO);

}
