package com.mc.resourcesshareback.task;

import java.util.function.Consumer;

public class TaskWrapper implements Runnable {

    private final String type;
    private final String paramsJson;
    private final Consumer<String> taskHandler;

    public TaskWrapper(String type, String paramsJson, Consumer<String> taskHandler) {
        this.type = type;
        this.paramsJson = paramsJson;
        this.taskHandler = taskHandler;
    }

    @Override
    public void run() {
        taskHandler.accept(paramsJson);
    }

    public String getType() {
        return type;
    }

    public String getParamsJson() {
        return paramsJson;
    }
}
