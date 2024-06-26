package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    List<ToDoList> findAllByAppUserEmail(String userEmail);

}
