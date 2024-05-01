package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.dtos.AppUserDTO;

public interface AppUserService {

    AppUserDTO getUserByEmail(String email);
    void saveUser(AppUserDTO userDTO);
    void updateUser(AppUserDTO userDTO);
    void deleteUser(String email);

    boolean checkIfUserExistsByEmail(String email);

}
