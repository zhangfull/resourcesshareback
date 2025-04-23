package com.mc.resourcesshareback.service;

import java.util.List;

import com.mc.resourcesshareback.pojo.Resource;

public interface ResourceMapper {

    boolean addResource(Resource resource);

    boolean deleteResource(Long resourceId);

    boolean updateResource(Resource resource);

    List<Resource> get(String resourceName, String resourceType, String version);

    Resource getResourceById(Long resourceId);

    List<Resource> getByProvider(Long providerId);

    boolean addDownloadCount(Long resourceId);


}
