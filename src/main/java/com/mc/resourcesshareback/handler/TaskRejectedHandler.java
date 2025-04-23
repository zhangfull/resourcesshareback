package com.mc.resourcesshareback.handler;

import java.time.LocalDateTime;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.stereotype.Component;

import com.mc.resourcesshareback.pojo.TaskEntity;
import com.mc.resourcesshareback.repository.TaskRepository;
import com.mc.resourcesshareback.task.TaskWrapper;
import com.mc.resourcesshareback.utils.LogUtil;

@Component
public class TaskRejectedHandler implements RejectedExecutionHandler {

    private final TaskRepository taskRepository; 
    public TaskRejectedHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (r instanceof TaskWrapper taskWrapper) {
            LogUtil.info(getClass(), "<!MAX!>:=The maximum number of threads is exceeded, and the database cache is started");
            TaskEntity entity = new TaskEntity();
            entity.setType(taskWrapper.getType());
            entity.setParams(taskWrapper.getParamsJson());
            entity.setCreatedAt(LocalDateTime.now());
            taskRepository.save(entity); // 存入数据库
        }
    }

}
