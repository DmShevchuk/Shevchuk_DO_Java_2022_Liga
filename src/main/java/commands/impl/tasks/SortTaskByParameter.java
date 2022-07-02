package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import exceptions.IncorrectArgsQuantityException;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;

import java.util.Comparator;
import java.util.List;

@Component
public class SortTaskByParameter extends Command {
    private final TaskService taskService;

    @Autowired
    public SortTaskByParameter(TaskService taskService) {
        super("sort_by_parameter", "|| sort all tasks by parameter", 1);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        List<TaskEntity> taskEntityList = taskService.getAll();
        if (taskEntityList.size() == 0){
            return "Collection of tasks is empty!";
        }
        String parameter = args[0];

        switch (parameter){
            case "title" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getTitle));
            case "description" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getDescription));
            case "deadline" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getDeadline));
            default -> taskEntityList.sort(Comparator.comparing(TaskEntity::getType));
        }

        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(Task.toModel(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
