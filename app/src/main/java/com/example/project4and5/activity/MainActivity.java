package com.example.project4and5.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4and5.R;
import com.example.project4and5.adapters.CustomAdapter;
import com.example.project4and5.interfaces.OnBottomReachedListener;
import com.example.project4and5.models.Member;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        refreshAdapter(prepareMemberList());

    }

    void initViews() {
        context = this;
        recyclerView = findViewById(R.id.recycler_view);
    }

    void refreshAdapter(ArrayList<Member> members) {
        CustomAdapter customAdapter = new CustomAdapter(context, members, new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                Log.d("@@@@@", "onBottomReached: " + position);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) throw new AssertionError();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    customAdapter.setItemList(prepareMemberList());
                    Toast.makeText(context, "stop!!", Toast.LENGTH_SHORT).show();
                    customAdapter.notifyDataSetChanged();
                    //you have reached to the bottom of your recycler view
                }
            }
        });

        recyclerView.setAdapter(customAdapter);
    }

    ArrayList<Member> prepareMemberList() {
        ArrayList<Member> members = new ArrayList<>();

        members.add(new Member()); // header item

        for (int i = 0; i <= 20; i++) {
            if (i == 0 || i == 10 || i == 17) {
                members.add(new Member("Muhammadrizo" + i, "Nurullaxo'jayev" + i, false));
            } else {
                members.add(new Member("Muhammadrizo" + i, "Nurullaxo'jayev" + i, true));
            }
        }

        members.add(new Member());  // footer item

        return members;
    }
}

