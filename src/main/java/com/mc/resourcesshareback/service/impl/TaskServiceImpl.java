package com.mc.resourcesshareback.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.resourcesshareback.service.TaskService;
import com.mc.resourcesshareback.task.TaskWrapper;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ExecutorService executorService;

    public void submitTask(String type, String paramsJson, Consumer<String> handler) {
        TaskWrapper task = new TaskWrapper(type, paramsJson, handler);
        executorService.execute(task);
    }
}

