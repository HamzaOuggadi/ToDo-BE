package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
