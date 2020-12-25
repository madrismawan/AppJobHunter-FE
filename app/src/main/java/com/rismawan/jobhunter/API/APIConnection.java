package com.rismawan.jobhunter.API;

public class APIConnection {
    public static final String BASE_URL = "http://192.168.0.100/jobHunter/public/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }

}
