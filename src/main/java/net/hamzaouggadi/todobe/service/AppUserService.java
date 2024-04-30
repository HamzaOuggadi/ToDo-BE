package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.AppUser;

public interface AppUserService {

    AppUser getUserByEmail(String email);
    AppUser saveUser(AppUser user);
    AppUser updateUser(AppUser user);
    void deleteUser(String email);
}
