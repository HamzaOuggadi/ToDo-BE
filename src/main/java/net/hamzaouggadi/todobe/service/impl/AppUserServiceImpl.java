package net.hamzaouggadi.todobe.service.impl;

import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.entities.AppUser;
import net.hamzaouggadi.todobe.exceptions.AppUserException;
import net.hamzaouggadi.todobe.repository.AppUserRepository;
import net.hamzaouggadi.todobe.service.AppUserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final MessageSource messageSource;


    @Override
    public AppUser getUserByEmail(String email) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmailIgnoreCase(email);
        if (appUserOptional.isPresent()) {
            return appUserOptional.get();
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "no.user.found",
                            new Object[]{email},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public AppUser saveUser(AppUser user) {
        if (!checkIfUserExistsByEmail(user.getEmail())) {
            user.setEmail(user.getEmail().toLowerCase());
            return appUserRepository.save(user);
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "user.already.exists",
                            new Object[]{user.getEmail()},
                            Locale.getDefault()
                    ),
                    HttpStatus.CONFLICT
            );
        }
    }

    @Override
    public AppUser updateUser(AppUser user) {
        if (checkIfUserExistsByEmail(user.getEmail())) {
            user.setEmail(user.getEmail().toLowerCase());
            return appUserRepository.save(user);
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "no.user.found",
                            new Object[]{user.getEmail()},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void deleteUser(String email) {
        if (checkIfUserExistsByEmail(email)) {
            appUserRepository.deleteByEmailIgnoreCase(email.toLowerCase());
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "no.user.found",
                            new Object[]{email},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    private boolean checkIfUserExistsByEmail(String email) {
        return appUserRepository.findByEmailIgnoreCase(email.toLowerCase()).isPresent();
    }
}
