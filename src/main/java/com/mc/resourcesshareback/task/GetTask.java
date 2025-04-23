package com.mc.resourcesshareback.task;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.mc.resourcesshareback.mapper.UserMapper;

@Component
public class GetTask {

    private final UserMapper userMapper;

    public GetTask(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Consumer<String> getTestTask(String taskType) {
        switch (taskType) {
            case "TEXT_TASK":
                Consumer<String> handler = paramJson -> {
                    try {
                        Thread.sleep(2000); // 等待 2 秒
                        userMapper.increaseFollower(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // 打印异常信息
                    }
                    System.out.println("任务完成！");
                };
                return handler;

            default:
                break;
        }

        return null;
    }

}
