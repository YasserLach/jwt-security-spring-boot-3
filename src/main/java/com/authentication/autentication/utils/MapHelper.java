package com.authentication.autentication.utils;

import com.authentication.autentication.dto.UserDto;
import com.authentication.autentication.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapHelper {

    MapHelper INSTANCE = Mappers.getMapper( MapHelper.class );

    UserDto entityToDto(User user);
    User dtoToentity(UserDto userDto);
}
