package com.mc.resourcesshareback.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.resourcesshareback.service.TaskService;
import com.mc.resourcesshareback.task.GetTask;

@RestController
public class TestController {

    private final TaskService taskService;
    private final GetTask getTask;

    // 构造方法注入 TaskService
    @Autowired
    public TestController(TaskService taskService, GetTask getTask) {

        this.taskService = taskService;
        this.getTask = getTask;
    }

    @GetMapping("/submitTask")
    public String submitTask() {

        

        String paramJson = "{\"userId\":\"" + 1 + "\"}";
        taskService.submitTask("TEXT_TASK", paramJson, getTask.getTestTask("TEXT_TASK"));
        return "已并发提交100个任务";
    }

}
