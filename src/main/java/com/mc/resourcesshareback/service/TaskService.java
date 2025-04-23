package com.mc.resourcesshareback.service;

import java.util.function.Consumer;

public interface TaskService {

    void submitTask(String type, String paramsJson, Consumer<String> handler);

}
