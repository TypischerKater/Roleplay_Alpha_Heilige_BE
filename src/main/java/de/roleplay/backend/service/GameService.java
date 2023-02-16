package de.roleplay.backend.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {
    private final TaskExecutor taskExecutor;

    private Map<UUID, Game> tasks = new HashMap<>();

    public GameService(@Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void addTask(Game task) {
        tasks.put(task.getId(), task);
        taskExecutor.execute(task);
        System.out.println("Task added with ID: " + task.getId());
    }

    public void removeTaskById(UUID id) {
        tasks.remove(id);
    }

    public Game getTaskById(UUID id) {
        return tasks.get(id);
    }
}
