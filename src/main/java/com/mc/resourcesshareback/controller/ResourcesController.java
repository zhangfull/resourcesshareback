package com.mc.resourcesshareback.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.resourcesshareback.interpretation.CheckLoginState;
import com.mc.resourcesshareback.pojo.Comment;
import com.mc.resourcesshareback.pojo.Resource;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.repository.CommentRepository;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/resources")
public class ResourcesController {

    private final CommentRepository commentRepository;

    public ResourcesController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Result<String> upload() {
        return Result.success();
    }

    public Result<String> delete() {
        return Result.success();
    }

    public Result<String> update() {
        return Result.success();
    }

    public Result<List<Resource>> get() {
        return Result.success();
    }

    public Result<Resource> getSingle() {
        return Result.success();
    }

    public Result<String> download() {
        return Result.success();
    }

    public Result<String> report() {
        return Result.success();
    }

    @CheckLoginState
    @PostMapping("send")
    public Result<String> comment(@RequestBody Comment comment, HttpServletRequest request) {
        try {
            // 尝试保存数据
            comment.setUserId(ThreadLocalUtil.getLongId());
            Comment saveComment = commentRepository.save(comment);
            System.out.println("保存成功，ID: " + saveComment.getId());
            return Result.success();
        } catch (DataIntegrityViolationException e) {
            // 处理数据库完整性约束违规异常
            System.err.println("数据完整性约束违规：" + e.getMessage());
        } catch (Exception e) {
            // 处理其他类型的异常
            System.err.println("保存失败：" + e.getMessage());
        }
        return Result.error(333, "失败");
    }

    @PostMapping("getAll")
    public Result<List<Comment>> getCommentList(@RequestParam("resourceId") Long id) {
        try {
            List<Comment> resources = commentRepository.findByResourceId(id);
            System.out.println("成功返回资源ID：" + id);
            return Result.success(resources);

        } catch (DataIntegrityViolationException e) {
            // 处理数据库完整性约束违规异常
            System.err.println("数据完整性约束违规：" + e.getMessage());
        } catch (Exception e) {
            // 处理其他类型的异常
            System.err.println("失败：" + e.getMessage());
        }
        return Result.error(333,"失败");
    }

    @CheckLoginState
    @PostMapping("delete")
    public Result<Boolean> deleteComment(@RequestParam("commentId") Long id, HttpServletRequest request) {

        try {
            if (commentRepository.findById(id).get().getUserId() != ThreadLocalUtil.getLongId()) {
                return Result.error(333,"只能删除自己评论");
            }
        } catch (Exception e) {
            System.err.println("失败：" + e.getMessage());
        }

        try {
            commentRepository.deleteById(id);
            System.out.println("成功，ID: " + id);
            return Result.success();
        } catch (DataIntegrityViolationException e) {
            // 处理数据库完整性约束违规异常
            System.err.println("数据完整性约束违规：" + e.getMessage());
        } catch (Exception e) {
            // 处理其他类型的异常
            System.err.println("失败：" + e.getMessage());
        }
        return Result.error(333,"失败");
    }

}
