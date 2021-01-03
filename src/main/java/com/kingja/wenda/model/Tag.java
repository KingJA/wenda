package com.kingja.wenda.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tag")
public class Tag {
    @Id
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 父级id
     */
    private Integer fid;

    /**
     * 标签等级 1：一级， 2：二级 
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取标签名
     *
     * @return name - 标签名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名
     *
     * @param name 标签名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父级id
     *
     * @return fid - 父级id
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置父级id
     *
     * @param fid 父级id
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 获取标签等级 1：一级， 2：二级 
     *
     * @return type - 标签等级 1：一级， 2：二级 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置标签等级 1：一级， 2：二级 
     *
     * @param type 标签等级 1：一级， 2：二级 
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
}