package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.services.TaskService;

/**
 *  Класс, реализующий функционал удаления всех задач
 * */
@RequiredArgsConstructor
public class ClearTasks implements Command {
    private final TaskService taskService;

    @Override
    public String execute() {
        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }
}
