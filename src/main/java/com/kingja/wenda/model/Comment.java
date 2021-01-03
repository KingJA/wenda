package com.kingja.wenda.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comment")
public class Comment {
    @Id
    private Integer id;

    /**
     * 父评论id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 评论等级
     */
    private Integer type;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 点赞数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 评论内容
     */
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父评论id
     *
     * @return parent_id - 父评论id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父评论id
     *
     * @param parentId 父评论id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取评论等级
     *
     * @return type - 评论等级
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置评论等级
     *
     * @param type 评论等级
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取点赞数
     *
     * @return like_count - 点赞数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞数
     *
     * @param likeCount 点赞数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取评论数
     *
     * @return comment_count - 评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}