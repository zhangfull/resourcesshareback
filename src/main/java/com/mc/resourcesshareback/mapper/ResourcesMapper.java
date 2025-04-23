package com.mc.resourcesshareback.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mc.resourcesshareback.pojo.Resource;

@Mapper
public interface ResourcesMapper {

    int addResource(Resource resource);

    int deleteResource(Long id);

    int updateResource(Resource resource);

    List<Resource> get(@Param("name") String name,
            @Param("type") String type,
            @Param("version") String version);

    Resource getResourceById(Long id);

    Resource getResourceForUpdate(Long id);

    List<Resource> getByProvider(Long id);

    int addDownloadCount(Long id);

}
