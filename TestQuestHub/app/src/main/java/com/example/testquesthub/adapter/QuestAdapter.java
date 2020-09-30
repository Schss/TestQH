package com.example.testquesthub.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquesthub.Models.Quest;
import com.example.testquesthub.R;

import java.util.ArrayList;

public class QuestAdapter extends RecyclerView.Adapter<QuestViewHolder> {
    private ArrayList<Quest> arrQuest = new ArrayList<Quest>();

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_view, parent, false)); //визуальная карточка
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {
        holder.bindData(arrQuest.get(position)); //вставка данных в карточку
    }

    @Override
    public int getItemCount() {
        return arrQuest.size();
    }

    public void refresh(ArrayList<Quest> aq) {
        arrQuest.clear();
        arrQuest.addAll(aq);
        notifyDataSetChanged();
    }
}
