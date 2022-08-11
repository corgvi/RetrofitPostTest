package com.example.retrofitposttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.retrofitposttest.api.ApiService;
import com.example.retrofitposttest.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnCallApi;
    private RecyclerView rcvCallApi;
    private List<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        CallApiAdapter callApiAdapter = new CallApiAdapter(list, this);
        rcvCallApi.setAdapter(callApiAdapter);
        rcvCallApi.setHasFixedSize(true);
        rcvCallApi.setLayoutManager(new LinearLayoutManager(this));


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvCallApi.addItemDecoration(dividerItemDecoration);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallApi();
            }
        });
    }

    private void clickCallApi() {
        ApiService.API_SERVICE.callProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                CallApiAdapter callApiAdapter = new CallApiAdapter(list, MainActivity.this);
                rcvCallApi.setAdapter(callApiAdapter);
                Toast.makeText(MainActivity.this, "Call api success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        btnCallApi = findViewById(R.id.btn_callAPI);
        rcvCallApi = findViewById(R.id.rcv_callAPI);
    }
}