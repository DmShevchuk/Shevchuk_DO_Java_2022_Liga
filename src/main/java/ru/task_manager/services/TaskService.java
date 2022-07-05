package ru.task_manager.services;

import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.repositories.TaskRepo;
import ru.task_manager.repositories.UserRepo;

import java.util.List;

/**
 * Класс для работы с базой данных и объектами класса {@link TaskEntity}
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public void create(TaskEntity task, Long userId) throws UserNotFoundException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }
        UserEntity user = userRepo.findById(userId).get();
        task.setUser(user);
        taskRepo.save(task);
    }

    public TaskEntity update(TaskEntity task) {
        return taskRepo.save(task);
    }

    public TaskEntity update(TaskEntity task, Long userId) throws UserNotFoundException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }
        UserEntity user = userRepo.findById(userId).get();
        task.setUser(user);
        return taskRepo.save(task);
    }

    public TaskEntity getById(Long id) throws TaskNotFoundException {
        if (!taskRepo.existsById(id)) {
            throw new TaskNotFoundException(id.toString());
        }
        return taskRepo.getReferenceById(id);
    }

    public List<TaskEntity> getAll() {
        return taskRepo.findAll();
    }

    public void delete(Long id) throws TaskNotFoundException {
        if (taskRepo.findById(id).isEmpty()) {
            throw new TaskNotFoundException(id.toString());
        }
        taskRepo.deleteById(id);
    }

    public void deleteAll() {
        taskRepo.deleteAll();
    }
}