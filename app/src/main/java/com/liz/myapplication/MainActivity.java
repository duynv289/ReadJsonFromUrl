package com.liz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liz.myapplication.enity.Lists;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ParseJsonFromUrlTask.OnFetchDataListener, ListAdapter.OnItemClickListener {

    private static final String API_LINK = "https://samples.openweathermap.org/data/2.5/" +
            "forecast?q=M%C3%BCnchen,DE&appid=b6907d289e10d714a6e88b30761fae22";
    private TextView txtList;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private int count = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtList = findViewById(R.id.txtList);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.GONE);
        txtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count % 2 == 0){
                    recyclerView.setVisibility(View.VISIBLE);
                }else{
                    recyclerView.setVisibility(View.GONE);
                }
                count++;
            }
        });
        new ParseJsonFromUrlTask(this).execute(API_LINK);
    }

    @Override
    public void onFetchData(ArrayList<Lists> lists) {
        listAdapter = new ListAdapter(lists);
        listAdapter.setOnItemClick(this);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void setOnItemClick(List<Lists> lists, int position) {
// TODO: 10/10/2018
    }
}
