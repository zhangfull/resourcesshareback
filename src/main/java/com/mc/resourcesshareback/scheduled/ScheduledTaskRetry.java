package com.mc.resourcesshareback.scheduled;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.resourcesshareback.pojo.TaskEntity;
import com.mc.resourcesshareback.repository.TaskRepository;
import com.mc.resourcesshareback.service.TaskService;
import com.mc.resourcesshareback.task.GetTask;

@Component
public class ScheduledTaskRetry {

    private final TaskRepository taskRepository;
    private final GetTask getTask;
    private final TaskService taskService;

    public ScheduledTaskRetry(TaskRepository taskRepository, TaskService taskService, GetTask getTask) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.getTask = getTask;
    }

    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    public void retryFailedTasks() {
        List<TaskEntity> pendingTasks = taskRepository.findAll();
        for (TaskEntity entity : pendingTasks) {
            taskService.submitTask(entity.getType(), entity.getParams(), getTask.getTestTask(entity.getType()));
            taskRepository.delete(entity);
        }

    }
}
