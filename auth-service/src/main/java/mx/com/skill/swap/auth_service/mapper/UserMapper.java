package mx.com.skill.swap.auth_service.mapper;

import mx.com.skill.swap.auth_service.dto.CreateUserDTO;
import mx.com.skill.swap.auth_service.dto.RegisterDTO;
import mx.com.skill.swap.auth_service.dto.UserDTO;
import mx.com.skill.swap.auth_service.dto.UserProfileDTO;
import mx.com.skill.swap.auth_service.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(RegisterDTO registerDTO);
    UserDTO toUserDTO(UserEntity userEntity);
    CreateUserDTO toCreateUserDTO(UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromUserProfileDTO(UserProfileDTO userProfileDTO, @MappingTarget UserEntity userEntity);

}
