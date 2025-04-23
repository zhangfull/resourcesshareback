package com.mc.resourcesshareback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.resourcesshareback.pojo.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
