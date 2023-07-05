package com.novare.natflax.NatflaxAdvance.Services;



import com.novare.natflax.NatflaxAdvance.Payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer user);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
