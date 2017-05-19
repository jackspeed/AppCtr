package com.ycj.adming.test;

import com.ycj.adming.test.bean.CityEntity;
import com.ycj.adming.test.bean.HomeInfoEntity;
import com.ycj.adming.test.bean.LoginBean;
import com.ycj.ycjlibrary.http.BaseResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by adming on 2017/5/4.
 */

public interface TestApi {
    //    获取咨询窗tab栏内容
    @GET("cms/articleClassifys/show")
    Observable<BaseResponse<List<HomeInfoEntity>>> getHomeInfoEntity();

    @GET("pub/city/select")
    Observable<BaseResponse<List<CityEntity>>> queryCity();

    //    用户登录接口
    @GET("pub/appUser/login")
    Observable<BaseResponse<LoginBean>> login(@Query("mobile") String mobile,
                                              @Query("password") String password);

}
