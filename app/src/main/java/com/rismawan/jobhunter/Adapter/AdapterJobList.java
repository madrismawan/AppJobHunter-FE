package com.rismawan.jobhunter.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.ActiveUser.Talent.RequestJobTalent;
import com.rismawan.jobhunter.ActiveUser.Talent.TalentActivity;
import com.rismawan.jobhunter.Auth.Login;
import com.rismawan.jobhunter.DataModel.TalentResponse.DataJob;
import com.rismawan.jobhunter.R;

import java.util.List;



public class AdapterJobList extends RecyclerView.Adapter<AdapterJobList.HolderDataJob> {

    private final Context context;
    private List<DataJob> getDataJobResponse;
    private String token;
    private BaseApiService baseApiService;

    public AdapterJobList(Context context, List<DataJob> getDataJobResponse, String token) {
        this.context = context;
        this.getDataJobResponse = getDataJobResponse;
        this.token = token;
    }

    @NonNull
    @Override
    public HolderDataJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_job, parent,false);
        HolderDataJob holderDataJob = new HolderDataJob(view);
        return  holderDataJob;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataJob holder, int position) {
        DataJob dataJob = getDataJobResponse.get(position);
        holder.idJob.setText(String.valueOf(dataJob.getId()));
        holder.namaPt.setText(dataJob.getNamaInstansi());
        holder.bagianPt.setText(dataJob.getBagian());

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestJob = new Intent(v.getContext(), RequestJobTalent.class);
                requestJob.putExtra("idJob",String.valueOf(dataJob.getId()));
                context.startActivity(requestJob);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getDataJobResponse.size();
    }

    public class HolderDataJob extends RecyclerView.ViewHolder {
        TextView namaPt,bagianPt,idJob;
        Button btn_detail;

        public HolderDataJob(@NonNull View itemView) {
            super(itemView);
            idJob = itemView.findViewById(R.id.idJob);
            namaPt = itemView.findViewById(R.id.listNamaptJob);
            bagianPt = itemView.findViewById(R.id.listBagianJob);
            btn_detail = itemView.findViewById(R.id.btnDetailJob);
        }
    }
}
