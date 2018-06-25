package edu.wat.pl.tim_lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText getValue;
    private ListView getList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.rButton);
        getValue = (EditText) findViewById(R.id.getValues);
        getList = (ListView) findViewById(R.id.randomList);



        final RandomService randomService = retrofit.create(RandomService.class);

        final ArrayAdapter<Integer> adapter =
                new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, new ArrayList<Integer>());

        adapter.setNotifyOnChange(true);

        getList.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = getValue.getText().toString();

                Call<List<Integer>> numbers = randomService.randomValues(amount);

                numbers.enqueue(new Callback<List<Integer>>() {
                    @Override
                    public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                        adapter.clear();
                        adapter.addAll(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<Integer>> call, Throwable t) {
                    }
                });
            }
        });

    }
}
