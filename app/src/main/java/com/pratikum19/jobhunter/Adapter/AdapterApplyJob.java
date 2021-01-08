package com.pratikum19.jobhunter.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.ActiveUser.Employer.KonfirmasiJob;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.ApplyJob.DetailDataJob;
import com.pratikum19.jobhunter.R;

import java.util.List;

public class AdapterApplyJob extends RecyclerView.Adapter<AdapterApplyJob.HolderDataJob>{


    private final Context context;
    private List<DetailDataJob> getDataDetail;
    private String token;
    private BaseApiService baseApiService;

    public AdapterApplyJob(Context context, List<DetailDataJob> getDataDetail, String token) {
        this.context = context;
        this.getDataDetail = getDataDetail;
        this.token = token;
    }

    @NonNull
    @Override
    public AdapterApplyJob.HolderDataJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_apply_job, parent,false);
        AdapterApplyJob.HolderDataJob holderDataJob = new AdapterApplyJob.HolderDataJob(view);
        return  holderDataJob;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterApplyJob.HolderDataJob holder, int position) {
        DetailDataJob dataJob = getDataDetail.get(position);
        holder.namaPt.setText(dataJob.getDataJoblist().getNamaInstansi());
        holder.bagianPt.setText(dataJob.getDataJoblist().getBagian());
        holder.namaPelamar.setText(dataJob.getUserDataJob().getName());

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestJob = new Intent(v.getContext(), KonfirmasiJob.class);
                requestJob.putExtra("idJob",String.valueOf(dataJob.getId()));
                context.startActivity(requestJob);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getDataDetail.size();
    }

    public class HolderDataJob extends RecyclerView.ViewHolder {
        TextView namaPt,bagianPt,namaPelamar;
        Button btn_detail;

        public HolderDataJob(@NonNull View itemView) {
            super(itemView);
//            idJob = itemView.findViewById(R.id.idJob);
            namaPt = itemView.findViewById(R.id.applyNama);
            bagianPt = itemView.findViewById(R.id.applyBagian);
            namaPelamar = itemView.findViewById(R.id.applyNamaPelamar);
            btn_detail = itemView.findViewById(R.id.btnPelamar);
        }
    }
}
