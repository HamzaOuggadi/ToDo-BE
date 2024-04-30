package net.hamzaouggadi.todobe.repository;

import net.hamzaouggadi.todobe.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
