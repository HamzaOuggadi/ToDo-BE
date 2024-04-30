package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

}
