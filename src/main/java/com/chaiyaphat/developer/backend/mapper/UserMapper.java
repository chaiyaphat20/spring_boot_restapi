package com.chaiyaphat.developer.backend.mapper;

import com.chaiyaphat.developer.backend.entity.User;
import com.chaiyaphat.developer.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
