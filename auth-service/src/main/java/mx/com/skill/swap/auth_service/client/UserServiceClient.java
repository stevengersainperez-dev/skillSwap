package mx.com.skill.swap.auth_service.client;


import mx.com.skill.swap.auth_service.dto.CreateUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {


    @PostMapping("users/create")
    void createUser(@RequestBody CreateUserDTO createUserDTO);

}
