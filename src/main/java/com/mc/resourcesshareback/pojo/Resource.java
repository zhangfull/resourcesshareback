package com.mc.resourcesshareback.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Resource {

    private Long id;
    private Long providerId;
    private String resourceName;
    private String customerName;
    private String resourceType;
    private String resourceSize;
    private LocalDateTime updateTime;
    private Long downloadCount;
    private String description;
    private String version;

}
