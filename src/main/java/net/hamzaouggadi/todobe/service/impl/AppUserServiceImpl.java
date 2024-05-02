package net.hamzaouggadi.todobe.service.impl;

import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.AppUserDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import net.hamzaouggadi.todobe.exceptions.AppUserException;
import net.hamzaouggadi.todobe.mappers.AppUserMapper;
import net.hamzaouggadi.todobe.repository.AppUserRepository;
import net.hamzaouggadi.todobe.service.AppUserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final MessageSource messageSource;
    private final AppUserMapper appUserMapper;


    @Override
    public AppUserDTO getUserByEmail(String email) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmailIgnoreCase(email);
        if (appUserOptional.isPresent()) {
            return appUserMapper.toAppUserDTO(appUserOptional.get());
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
    public void saveUser(AppUserDTO userDTO) {
        if (!checkIfUserExistsByEmail(userDTO.email())) {
            AppUser appUser = appUserMapper.toAppUser(userDTO);
            appUser.setRegistrationDate(LocalDateTime.now());
            appUserRepository.save(appUser);
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "user.already.exists",
                            new Object[]{userDTO.email()},
                            Locale.getDefault()
                    ),
                    HttpStatus.CONFLICT
            );
        }
    }

    @Override
    public void updateUser(AppUserDTO userDTO) {
        AppUser appUser = appUserRepository.findByEmailIgnoreCase(userDTO.email().toLowerCase()).orElseThrow(
                () -> new AppUserException(messageSource.getMessage(
                    "no.user.found",
                    new Object[]{userDTO.email()},
                    Locale.getDefault()
        )));
        appUser.setPassword(userDTO.password());
        appUser.setProfilePicture(userDTO.profilePicture());
        appUserRepository.save(appUser);
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


    @Override
    public boolean checkIfUserExistsByEmail(String email) {
        return appUserRepository.findByEmailIgnoreCase(email.toLowerCase()).isPresent();
    }
}
