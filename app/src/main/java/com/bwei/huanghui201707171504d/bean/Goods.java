package com.bwei.huanghui201707171504d.bean;

/**
 * 类注释 ：实体类
 * 创建人：黄慧
 * 创建时间： 2017/7/17.9:43
 */

public class Goods {
    //属性
    private String title;
    private String money;
    private boolean flag;
    public Goods(String title, String money, boolean flag) {
        super();
        this.title = title;
        this.money = money;
        this.flag = flag;
    }
    public Goods() {
        super();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    @Override
    public String toString() {
        return "Goods [title=" + title + ", money=" + money + ", flag=" + flag
                + "]";
    }


}
