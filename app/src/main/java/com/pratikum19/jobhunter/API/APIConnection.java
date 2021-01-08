package com.pratikum19.jobhunter.API;

public class APIConnection {
    public static final String BASE_URL = "http://192.168.0.100/JobHunter/public/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }

}
