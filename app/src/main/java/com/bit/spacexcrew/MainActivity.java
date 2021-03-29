package com.bit.spacexcrew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Crew> data = new ArrayList<>();
    LinearLayoutManager manager;
    CrewDatabase database;
    DataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button refresh,delete;
        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler);
        refresh = findViewById(R.id.btn_refresh);
        delete = findViewById(R.id.btn_delete);
        database = CrewDatabase.getInstance(this);
        data = database.crewDao().getAll();
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new DataAdapter(data, MainActivity.this);
        recyclerView.setAdapter(adapter);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.crewDao().DeleteAll();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://api.spacexdata.com/v4/crew";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            for(int index=0;index<response.length();index++) {
                                try{
                                JSONObject object = response.getJSONObject(index);
                                Crew crew = new Crew(object.getString("name"),object.getString("agency"),object.getString("image"),object.getString("wikipedia"),object.getString("status"));
                                database.crewDao().insert(crew);
                                data = database.crewDao().getAll();
                                adapter = new DataAdapter(data, MainActivity.this);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, "JSON Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.crewDao().DeleteAll();
                data = database.crewDao().getAll();
                adapter = new DataAdapter(data, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}