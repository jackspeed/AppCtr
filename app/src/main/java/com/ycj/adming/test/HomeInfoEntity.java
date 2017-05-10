package com.ycj.adming.test;

/**
 * Created by adming on 2017/5/4.
 */

public class HomeInfoEntity {
    /**
     * createtime : 2017-02-14 16:18:48
     * id : 1
     * modifytime : 2017-02-14 16:18:49
     * name : 资讯
     * pid : null
     * sort : 1
     * status : 1
     */

    private String createtime;
    private int id;
    private String modifytime;
    private String name;
    private String pid;
    private int sort;
    private String status;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeInfoEntity{" +
                "createtime='" + createtime + '\'' +
                ", id=" + id +
                ", modifytime='" + modifytime + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                '}';
    }
}
