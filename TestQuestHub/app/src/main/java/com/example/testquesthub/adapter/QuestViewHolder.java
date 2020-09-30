package com.example.testquesthub.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquesthub.Models.Quest;
import com.example.testquesthub.R;

public class QuestViewHolder extends RecyclerView.ViewHolder {
    public QuestViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindData(Quest quest) {
        TextView nq = itemView.findViewById(R.id.name_quest);
        nq.setText(quest.getName());

        TextView aq = itemView.findViewById(R.id.ad_quest);
        aq.setText("Адресс квеста: " + quest.getAd());

        TextView iq = itemView.findViewById(R.id.info_quest);
        iq.setText("Дополнительная информация: " + quest.getAd());
    }
}
