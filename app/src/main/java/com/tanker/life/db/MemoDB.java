package com.tanker.life.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 待办事项数据库实体类
 */
@Entity
public class MemoDB {
    @Id
    private Long id;
    private String title;
    private String content;
    private Date creatTime;
    private Date runTime;
    @Generated(hash = 1222905993)
    public MemoDB(Long id, String title, String content, Date creatTime,
            Date runTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatTime = creatTime;
        this.runTime = runTime;
    }
    @Generated(hash = 445873150)
    public MemoDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatTime() {
        return this.creatTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public Date getRunTime() {
        return this.runTime;
    }
    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

}