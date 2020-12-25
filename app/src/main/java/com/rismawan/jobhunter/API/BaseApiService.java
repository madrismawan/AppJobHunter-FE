package com.rismawan.jobhunter.API;

import com.rismawan.jobhunter.DataModel.EmployerResponse.AddJobRespone;
import com.rismawan.jobhunter.DataModel.EmployerResponse.ApplyJob.ReqJobResponse;
import com.rismawan.jobhunter.DataModel.EmployerResponse.DeleteJobResponse;
import com.rismawan.jobhunter.DataModel.EmployerResponse.JobListResponse;
import com.rismawan.jobhunter.DataModel.EmployerResponse.KonfirmasiJob.KonfirmasiResponse;
import com.rismawan.jobhunter.DataModel.EmployerResponse.UpdateJobResponse;
import com.rismawan.jobhunter.DataModel.LoginResponse.LoginRespone;
import com.rismawan.jobhunter.DataModel.RegisterResponse.RegisRespone;
import com.rismawan.jobhunter.DataModel.TalentResponse.DetailJobResponse;
import com.rismawan.jobhunter.DataModel.TalentResponse.GetDataJobResponse;
import com.rismawan.jobhunter.DataModel.TalentResponse.RequestJobResponse;
import com.rismawan.jobhunter.DataModel.UserResponse.DataUser;
import com.rismawan.jobhunter.DataModel.UserResponse.UpdateUserResponse;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginRespone> login(@Field("email") String email,
                             @Field("password") String password);

    @Headers(
            "Accept: application/json"
    )
    @GET("logout")
    Call<LoginRespone> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("registrasi")
    Call<RegisRespone> registrasi (@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("password") String password,
                                   @Field("password_confirmation") String password_confirmation,
                                   @Field("role") String role
    );


    @GET("user")
    Call<DataUser> getUser(@Header("Authorization") String token);


    @Headers(
            "Accept: application/json"
    )
    @FormUrlEncoded
    @POST("user-update")
    Call<UpdateUserResponse> userUpdate (@Header("Authorization") String token,
                                        @Field("name") String name,
                                        @Field("notlpn") String notlpn,
                                        @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("talent-update")
    Call<UpdateUserResponse> talentUpdate (@Header("Authorization") String token,
                                           @Field("name") String name,
                                           @Field("notlpn") String notlpn,
                                           @Field("alamat") String alamat,
                                           @Field("umur") String umur,
                                           @Field("gender") String gender
    );



    @GET("job_employer")
    Call<JobListResponse> getJobEmployer(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("create_job")
    Call<AddJobRespone> createJob (@Header("Authorization") String token,
                                    @Field("nama_instansi") String nama_instansi,
                                    @Field("alamat") String alamat,
                                    @Field("bagian") String bagian,
                                    @Field("syarat") String syarat
    );

    @FormUrlEncoded
    @POST("update-job/{id_job}")
    Call<UpdateJobResponse> updateJob (@Header("Authorization") String token,
                                       @Path("id_job") String id_job,
                                       @Field("nama_instansi") String nama_instansi,
                                       @Field("alamat") String alamat,
                                       @Field("bagian") String bagian,
                                       @Field("syarat") String syarat
    );


    @GET("delete_job/{id_job}")
    Call<DeleteJobResponse> deleteJob(@Header("Authorization") String token,
                                      @Path("id_job") Integer id_job);


    @GET("job_list")
    Call<GetDataJobResponse> getDataJob(@Header("Authorization") String token);

    @GET("detail-job-list/{id_job}")
    Call<DetailJobResponse> detailDataJob (@Header("Authorization") String token,
                                           @Path("id_job") String id_job

    );

    @FormUrlEncoded
    @POST("send-request-job/{id_job}")
    Call<RequestJobResponse> lamarJob (@Header("Authorization") String token,
                                       @Path("id_job") String id_job,
                                       @Field("pengalaman") String pengalaman
    );

    @GET("request-job")
    Call<ReqJobResponse> reqJobTalent (@Header("Authorization") String token);


    @GET("detail-request/{id_detail_job}")
    Call<KonfirmasiResponse> detailPelamar (@Header("Authorization") String token,
                                            @Path("id_detail_job") String id_job

    );

    @FormUrlEncoded
    @POST("apply-job/{id_job}")
    Call<RequestJobResponse> applyJob (@Header("Authorization") String token,
                                       @Path("id_job") String id_job,
                                       @Field("status") String status
    );




}
