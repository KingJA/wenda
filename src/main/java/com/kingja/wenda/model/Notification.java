package com.kingja.wenda.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notification")
public class Notification {
    @Id
    private Integer id;

    /**
     * 发送者id
     */
    @Column(name = "receiver_id")
    private Integer receiverId;

    /**
     * 接收者id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 载体id
     */
    @Column(name = "target_id")
    private Integer targetId;

    /**
     * 外部id
     */
    @Column(name = "outer_id")
    private Integer outerId;

    /**
     * 发送者名称
     */
    @Column(name = "sender_name")
    private String senderName;

    /**
     * 载体标题
     */
    @Column(name = "target_title")
    private String targetTitle;

    /**
     * 通知类型 1.问题评价 2.评价评价
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 已读状态 0.未读 1.已读
     */
    private Boolean status;

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
     * 获取发送者id
     *
     * @return receiver_id - 发送者id
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * 设置发送者id
     *
     * @param receiverId 发送者id
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取接收者id
     *
     * @return sender_id - 接收者id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置接收者id
     *
     * @param senderId 接收者id
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取载体id
     *
     * @return target_id - 载体id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * 设置载体id
     *
     * @param targetId 载体id
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取外部id
     *
     * @return outer_id - 外部id
     */
    public Integer getOuterId() {
        return outerId;
    }

    /**
     * 设置外部id
     *
     * @param outerId 外部id
     */
    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    /**
     * 获取发送者名称
     *
     * @return sender_name - 发送者名称
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * 设置发送者名称
     *
     * @param senderName 发送者名称
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    /**
     * 获取载体标题
     *
     * @return target_title - 载体标题
     */
    public String getTargetTitle() {
        return targetTitle;
    }

    /**
     * 设置载体标题
     *
     * @param targetTitle 载体标题
     */
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle == null ? null : targetTitle.trim();
    }

    /**
     * 获取通知类型 1.问题评价 2.评价评价
     *
     * @return type - 通知类型 1.问题评价 2.评价评价
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置通知类型 1.问题评价 2.评价评价
     *
     * @param type 通知类型 1.问题评价 2.评价评价
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取已读状态 0.未读 1.已读
     *
     * @return status - 已读状态 0.未读 1.已读
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置已读状态 0.未读 1.已读
     *
     * @param status 已读状态 0.未读 1.已读
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}