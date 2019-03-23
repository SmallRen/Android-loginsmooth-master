package com.wzh.study.history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wzh.study.Config;
import com.wzh.study.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecycleViewAdapter mRecycleViewAdapter;
    private List<Sensor> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        loadData();
    }

    private void loadData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Config.ADDRESS + "/sensor/all")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String body = response.body().string();
                Log.d("body", body);
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray data = jsonObject.getJSONArray("data");
                    if (data != null) {
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object = data.getJSONObject(i);
                            Sensor sensor = new Sensor();
                            sensor.setTemperature(object.getString("temperature"));
                            sensor.setLiquidLevel(object.getString("levelLndicators"));
                            sensor.setHeartRate(object.getString("heartRate"));
                            object.getString("collectDate");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date collectDate = simpleDateFormat.parse(object.getString("collectDate"));
                                String dataString = spf.format(collectDate);
                                sensor.setCollectDate(dataString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            mData.add(sensor);

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRecycleViewAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void init() {
        mRecyclerView = findViewById(R.id.recycle_view);
        //初始化线性布局管理器
        mLinearLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //初始化适配器
        mRecycleViewAdapter = new RecycleViewAdapter(mData);
        //设置适配器
        mRecyclerView.setAdapter(mRecycleViewAdapter);
    }
}
