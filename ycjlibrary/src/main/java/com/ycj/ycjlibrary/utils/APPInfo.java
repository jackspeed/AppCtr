package com.ycj.ycjlibrary.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class AppInfo {

    private static Context mContext;
    public static long uiTid;

    public static int version;
    public static String packageName;

    public static String clientType;

    public static String versionName;


    /**
     * 推广渠道Id，后期推广使用
     */
//	private String channelId;
    public static void init(Context context) {
        AppInfo.setContext(context);

        uiTid = Thread.currentThread().getId();

    }


    public   void initAppInfo(   ) {
        try {
            packageName = mContext.getPackageName();
            //获取版本号与版本名称
            PackageInfo packInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
            version = packInfo.versionCode;
            versionName = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        AppInfo.mContext = context;
    }

    public static long getUiTid() {
        return uiTid;
    }

    public static int getVersion() {
        return version;
    }

    public static String getVersionName() {
        return versionName;
    }

    public static String getPackageName() {
        return packageName;
    }


    /**
     * @return the clientType
     */
    public static String getClientType() {
        return clientType;
    }

    /**
     * @param client the clientType to set
     */
    public static void setClientType(String client) {
        AppInfo.clientType = client;
    }


    public static String getDeviceId() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) mContext
                        .getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds") String imei = mTelephonyMgr.getDeviceId();
        if (imei != null && imei.length() > 6) {
            return imei;
        } else {
            @SuppressLint("HardwareIds") String androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (androidId != null && androidId.length() > 6) {
                return androidId;
            }
        }
        return "000000000000000";
    }
}
