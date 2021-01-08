package com.pratikum19.jobhunter.ActiveUser.Employer.Fragement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.DataModel.LoginResponse.LoginRespone;
import com.pratikum19.jobhunter.DataModel.UserResponse.DataUser;
import com.pratikum19.jobhunter.DataModel.UserResponse.UpdateUserResponse;
import com.pratikum19.jobhunter.MainActivity;
import com.pratikum19.jobhunter.R;
import com.pratikum19.jobhunter.Session.SessionSave;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private BaseApiService baseApiService;
    private EditText name,tlpn,alamat;
    private Button update;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button logout = view.findViewById(R.id.btnLogout);
        baseApiService = APIConnection.getApiService();
        getDataUser(view);
        Button update = view.findViewById(R.id.btnUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = view.findViewById(R.id.upNama);
                alamat = view.findViewById(R.id.upAlamat);
                tlpn = view.findViewById(R.id.upTlpn);
                String upNama = name.getText().toString();
                String upTlpn = tlpn.getText().toString();
                String upAlamat = alamat.getText().toString();
                SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
                String Token = getToken.getString("token", "");

                baseApiService.userUpdate(Token,upNama,upTlpn,upAlamat).enqueue(new Callback<UpdateUserResponse>() {
                    @Override
                    public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                        Toast.makeText(getActivity(), "response.body().getMessage()", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
                String Token = getToken.getString("token", "");
                BaseApiService baseApiService = APIConnection.getApiService();
                baseApiService.logout(Token).enqueue(new Callback<LoginRespone>() {
                    @Override
                    public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                        SessionSave sessionLogin = new SessionSave();
                        sessionLogin.deleteSession(getActivity());
                        Toast.makeText(getActivity(), "Logout Berhasil !!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LoginRespone> call, Throwable t) {
                        Toast.makeText(getActivity(), "Gagal Menghungkan ke server", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });


    }



    private void getDataUser(View view) {
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        baseApiService.getUser(Token).enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                name = view.findViewById(R.id.upNama);
                alamat = view.findViewById(R.id.upAlamat);
                tlpn = view.findViewById(R.id.upTlpn);
                name.setText(response.body().getName());
                tlpn.setText(response.body().getNotlpn());
                alamat.setText(response.body().getAlamat());

            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
