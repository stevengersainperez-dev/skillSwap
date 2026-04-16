package mx.com.skill.swep.user_service.mapper;


import mx.com.skill.swep.user_service.dto.CreateUserDTO;
import mx.com.skill.swep.user_service.dto.UserProfileResponseDTO;
import mx.com.skill.swep.user_service.dto.UserProfileUpdateRequestDTO;
import mx.com.skill.swep.user_service.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfileResponseDTO fromUserEntitytoUserProfileResponseDTO(UserEntity userEntity);
    UserEntity fromCreateUserDTOtoUserEntity(CreateUserDTO createUserDTO);
    List<UserProfileResponseDTO> fromUserEntityListToUserProfileResponseDTOList (List<UserEntity> userEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromUserProfileUpdateDTO(UserProfileUpdateRequestDTO dto, @MappingTarget UserEntity entity);

}
