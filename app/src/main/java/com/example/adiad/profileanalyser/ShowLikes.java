package com.example.adiad.profileanalyser;

        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowLikes extends AppCompatActivity {


    AccessToken currentAccessToken;
    TextView disp;
    RequestQueue queue;
    List<String> likes = new ArrayList<String>();

    String getnext(JSONObject obj) {
        String next_url ="";
        try {
           if(obj.getJSONObject("paging").has("next"))
            {
                next_url= obj.getJSONObject("paging").getString("next");
            }
            //disp.setText(next_url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return next_url;
    }

    void makeRequest(String url) {
        //disp.setText(url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    show_likes(response.getJSONArray("data"));
                    //Log.i("Json",response.getJSONArray("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String  next=getnext(response);
                if (!next.isEmpty())
                {
                    makeRequest(next);
                }
                disp.setText(likes.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        queue.add(jsObjRequest);

    }
    void show_likes(JSONArray objects) throws JSONException {
        //disp.setText(objects.getJSONObject(0).toString());
       for (int i = 0; i < objects.length(); ++i) {
            JSONObject object = null;
            try {
                object = objects.getJSONObject(i);
               likes.add(object.getString("category"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_likes2);
        Bundle extras = getIntent().getExtras();
        currentAccessToken = (AccessToken) extras.get("currentAccessToken");
        disp = (TextView) findViewById(R.id.disp);
        queue = Volley.newRequestQueue(this);
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                       try {
                            show_likes(object.getJSONObject("likes").getJSONArray("data"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String next = null;
                        try {
                            next = getnext(object.getJSONObject("likes"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //disp.setText(next);
                        if(!next.isEmpty()){
                           makeRequest(next);
                        }


                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "likes{category}");
        request.setParameters(parameters);
        request.executeAsync();
    }
}