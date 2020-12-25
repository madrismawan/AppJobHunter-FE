package com.rismawan.jobhunter.ActiveUser.Talent.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.DataModel.LoginResponse.LoginRespone;
import com.rismawan.jobhunter.DataModel.UserResponse.DataUser;
import com.rismawan.jobhunter.DataModel.UserResponse.UpdateUserResponse;
import com.rismawan.jobhunter.MainActivity;
import com.rismawan.jobhunter.R;
import com.rismawan.jobhunter.Session.SessionSave;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeTalentFragment extends Fragment {
    private BaseApiService baseApiService;
    private EditText name,tlpn,alamat,umur;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_talent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");

        Button btnLogout = view.findViewById(R.id.btnLogoutTalent);
        baseApiService = APIConnection.getApiService();
        getDataUser(view,Token);
        Button btnUpdate = view.findViewById(R.id.btnUpdateTalent);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = view.findViewById(R.id.upNamaTalent);
                alamat = view.findViewById(R.id.upAlamatTalent);
                tlpn = view.findViewById(R.id.upTlpnTalent);
                Spinner gender = view.findViewById(R.id.spinerGender);
                umur = view.findViewById(R.id.umurTalent);

                String upNama = name.getText().toString();
                String upTlpn = tlpn.getText().toString();
                String upAlamat = alamat.getText().toString();
                String upUmur = umur.getText().toString();
                String upGender = gender.getSelectedItem().toString();

                baseApiService.talentUpdate(Token,upNama,upTlpn,upAlamat,upUmur,upGender).enqueue(new Callback<UpdateUserResponse>() {
                    @Override
                    public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                        Toast.makeText(getActivity(), "Data Anda Berhasil Terupdate", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


    private void getDataUser(View view, String token) {

        baseApiService.getUser(token).enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                name = view.findViewById(R.id.upNamaTalent);
                alamat = view.findViewById(R.id.upAlamatTalent);
                tlpn = view.findViewById(R.id.upTlpnTalent);
                umur = view.findViewById(R.id.umurTalent);
                name.setText(response.body().getName());
                tlpn.setText(response.body().getNotlpn());
                alamat.setText(response.body().getAlamat());
                umur.setText(response.body().getUmur());

                String compareValue = response.body().getGender();
                Spinner spinner = view.findViewById(R.id.spinerGender);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.gender, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                if (compareValue != null) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    spinner.setSelection(spinnerPosition);
                }

            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });


    }
}