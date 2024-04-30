package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmailIgnoreCase(String email);

    void deleteByEmailIgnoreCase(String email);

}
