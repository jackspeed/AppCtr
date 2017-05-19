package com.ycj.adming.test.bean;

/**
 * Created by adming on 2017/5/4.
 */

public class CityEntity {

    /**
     * code : 330100
     * name : 杭州市
     */
    private Long indexs;
    private String code;
    private String name;
    private String codebak;

    public CityEntity(Long indexs, String code, String name, String codebak) {
        this.indexs = indexs;
        this.code = code;
        this.name = name;
        this.codebak = codebak;
    }

    public CityEntity() {
    }

    public Long getIndexs() {
        return indexs;
    }

    public void setIndexs(Long indexs) {
        this.indexs = indexs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodebak() {
        return codebak;
    }

    public void setCodebak(String codebak) {
        this.codebak = codebak;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", codebak='" + codebak + '\'' +
                '}';
    }

}
