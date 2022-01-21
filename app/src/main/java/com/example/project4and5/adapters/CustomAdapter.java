package com.example.project4and5.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4and5.R;
import com.example.project4and5.interfaces.OnBottomReachedListener;
import com.example.project4and5.models.Member;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_AVAILABLE_YES = 1;
    private static final int TYPE_AVAILABLE_NOT = 2;
    private static final int TYPE_FOOTER = 3;


    public final Context context;
    private final ArrayList<Member> itemList;
    private final OnBottomReachedListener onBottomReachedListener;


    public CustomAdapter(Context context, ArrayList<Member> itemList, OnBottomReachedListener onBottomReachedListener) {
        this.itemList = itemList;
        this.context = context;
        this.onBottomReachedListener = onBottomReachedListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(ArrayList<Member> list) {
        if (this.itemList.size() + list.size() <= 100) {
            this.itemList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else if (itemList.get(position).getAvailable()) {
            return TYPE_AVAILABLE_YES;
        } else {
            return TYPE_AVAILABLE_NOT;
        }
    }

    boolean isHeader(int position) {
        return position == 0;
    }

    boolean isFooter(int position) {
        return position == itemList.size() - 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            return new CustomViewHeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false));
        } else if (viewType == TYPE_FOOTER) {
            return new CustomViewFooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
        } else if (viewType == TYPE_AVAILABLE_YES) {
            return new CustomViewYesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false));
        } else {
            return new CustomViewNotHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offline_member, parent, false));
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == itemList.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }
        if (isHeader(position) || isFooter(position)) return;


        Member member = itemList.get(position);

        if (holder instanceof CustomViewYesHolder) {
            TextView txt_name_online = holder.itemView.findViewById(R.id.txt_name_online);
            TextView txt_surname_online = holder.itemView.findViewById(R.id.txt_surname_online);

            txt_name_online.setText(member.getName());
            txt_surname_online.setText(member.getSurname());

        } else if (holder instanceof CustomViewNotHolder) {
            TextView txt_name_offline = holder.itemView.findViewById(R.id.txt_name_offline);
            TextView txt_surname_offline = holder.itemView.findViewById(R.id.txt_surname_offline);

            txt_name_offline.setText("Member is blocked");
            txt_surname_offline.setText("Member is blocked");

        } else if (holder instanceof CustomViewHeaderHolder) {
            TextView header_txt = holder.itemView.findViewById(R.id.header_txt);
            header_txt.setText("This is header item");
        } else if (holder instanceof CustomViewFooterHolder) {
            TextView footer_txt = holder.itemView.findViewById(R.id.footer_txt);
            footer_txt.setText("This is footer item");
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    private static final class CustomViewYesHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView txt_name_online, txt_surname_online;

        public CustomViewYesHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            txt_name_online = view.findViewById(R.id.txt_name_online);
            txt_surname_online = view.findViewById(R.id.txt_surname_online);
        }
    }


    private static final class CustomViewNotHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView txt_name_offline, txt_surname_offline;

        public CustomViewNotHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            txt_name_offline = view.findViewById(R.id.txt_name_offline);
            txt_surname_offline = view.findViewById(R.id.txt_surname_offline);
        }
    }

    private static final class CustomViewHeaderHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView header_txt;

        public CustomViewHeaderHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            header_txt = view.findViewById(R.id.header_txt);
        }
    }


    private static final class CustomViewFooterHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView footer_txt;

        public CustomViewFooterHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            footer_txt = view.findViewById(R.id.footer_txt);
        }
    }
}
