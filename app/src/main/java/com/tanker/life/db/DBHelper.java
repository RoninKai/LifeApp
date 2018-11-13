package com.tanker.life.db;

import com.tanker.life.bean.MemoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 数据库操作辅助类
 */
public class DBHelper {

    private static DBHelper helper;

    private MemoHelper memoHelper;

    public static DBHelper getInstance() {
        if (helper == null) {
            synchronized (DBHelper.class) {
                if (helper == null) {
                    helper = new DBHelper();
                }
            }
        }
        return helper;
    }

    public MemoHelper getMemo() {
        if (memoHelper == null) {
            memoHelper = new MemoHelper();
        }
        return memoHelper;
    }

    /**
     * 待办事项
     */
    public class MemoHelper {

        /**
         * 加载所有的待办事项
         *
         * @return
         */
        public List<MemoBean> getMemoData() {
            List<MemoBean> memoList = new ArrayList<>();
            List<MemoDB> memoDBList = getMemoAsDB();
            if (memoDBList != null) {
                for (MemoDB memoDB : memoDBList) {
                    memoList.add(new MemoBean(memoDB.getId(), memoDB.getTitle(), memoDB.getContent()));
                }
            }
            return memoList;
        }

        /**
         * 添加一条事项
         *
         * @param title
         * @param content
         */
        public void addMemo(String title,String content){
            MemoDB memoDB = new MemoDB();
            memoDB.setTitle(title);
            memoDB.setContent(content);
            addMemo(memoDB);
        }

        /**
         * 加载所有的待办事项（数据库查询）
         *
         * @return
         */
        private List<MemoDB> getMemoAsDB() {
            return DBManager.getInstance().getDaoSession().getMemoDBDao().loadAll();
        }

        /**
         * 添加一条事项
         *
         * @param memo
         */
        private void addMemo(MemoDB memo) {
            DBManager.getInstance().getDaoSession().getMemoDBDao().insert(memo);
        }

        /**
         * 根据id删除事项
         *
         * @param memoId
         */
        private void delectMemo(Long memoId) {
            DBManager.getInstance().getDaoSession().getMemoDBDao().deleteByKey(memoId);
        }

        /**
         * 根据实体类删除事项
         *
         * @param memo
         */
        private void delectMemo(MemoDB memo) {
            DBManager.getInstance().getDaoSession().getMemoDBDao().delete(memo);
        }

    }

}