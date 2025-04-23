package com.mc.resourcesshareback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mc.resourcesshareback.pojo.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByResourceId(Long resourceId);

}
