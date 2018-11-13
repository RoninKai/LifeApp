package com.tanker.life.bean;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 待办事项实体类
 */
public class MemoBean {

    private Long memoId;
    private String title;
    private String content;

    public long getMemoId() {
        return memoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public MemoBean(Long memoId,String title,String content){
        this.memoId = memoId;
        this.title = title;
        this.content = content;
    }

}