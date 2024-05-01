package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByToDoListAppUserEmailIgnoreCase(String email);

    List<Task> findAllByToDoListId(Long toDoListId);

}
