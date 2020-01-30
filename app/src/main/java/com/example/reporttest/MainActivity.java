package com.example.reporttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;

    String url = "https://api.myjson.com/bins/ymr7y";


    RecyclerView recyclerView;
    ArticleAdapter adapter;
    ArrayList<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticleAdapter();
        recyclerView.setAdapter(adapter);
        articles = new ArrayList<>();
        getData();
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

      /*  JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("actor");

                            for (int i=0; i< response.length(); i++){
                                JSONObject actor = response.getJSONObject(String.valueOf(i));

                                String avatar_url = actor.getString("avatar_url");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });*/

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

               try {
                   for(int i=0; i<response.length(); i++) {
                       JSONObject jsonObject = response.getJSONObject(i);
                       Article article = new Article();
                       article.setAvatar_url(jsonObject.getString("avatar_url"));
                       article.setId(jsonObject.getString("id"));
                       article.setType(jsonObject.getString("login"));
                       article.setDisplay_login(jsonObject.getString("display_login"));
                       article.setGravatar_id(jsonObject.getString("gravatar_id"));
                       article.setUrl(jsonObject.getString("url"));
                       articles.add(article);





                   }

               }
               catch (JSONException e){

                   Toast.makeText(MainActivity.this,"JSON is not valid",Toast.LENGTH_SHORT).show();

               }

                adapter.setData(articles);
               adapter.notifyDataSetChanged();
               progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error Occurred",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
