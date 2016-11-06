package com.example.adiad.profileanalyser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.GraphUtil;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Lavish extends AppCompatActivity implements View.OnClickListener{
    CallbackManager callbackManager;
    public static String var="";
    Button btn3;
    TextView tv1;
    ArrayList<String> likes ;
    public PieChart mPieChart;
    public Float anger=0.0f,disgust=0.0f,contempt=0.0f,fear=0.0f,happpiness=0.0f,neutral=0.0f,surprise=0.0f,sadness=0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.lavish);

        btn3=(Button)findViewById(R.id.button3);

        tv1=(TextView)findViewById(R.id.textView2);
        likes = new ArrayList<String>();
        mPieChart=(PieChart)findViewById(R.id.piechart);

        btn3.setOnClickListener(this);

        AppEventsLogger.activateApp(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_friends","user_likes","user_posts","user_photos","public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                   /*/*/*/*/**/*/*/*/*//**/


//                  /*/*/*/*/*/**/*/*/*/*/*/
                    }
                }).start();

            }   //loginResult

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void make_pie_chart(){

        mPieChart.clearChart();
        int col = Color.parseColor("#FE6DA8");
        col=col+4000;
        mPieChart.addPieSlice(new PieModel("Anger", anger*1000, col));
        col=col+9000;
        mPieChart.addPieSlice(new PieModel("Disgust" ,disgust*1000, col));
        col=col+7000;
        mPieChart.addPieSlice(new PieModel("Happiness" ,happpiness*1000, col));
        col=col+1000;
        mPieChart.addPieSlice(new PieModel("Sadness" ,sadness*1000, col));
        col=col+1000;
        mPieChart.addPieSlice(new PieModel("Surprise" ,surprise*1000, col));
        col=col+1000;
        mPieChart.addPieSlice(new PieModel("Contempt" ,contempt*1000, col));
        col=col+1000;
        mPieChart.addPieSlice(new PieModel("Neutral" ,neutral*1000, col));
    }

    private void retreiveposts() {
        Fetchdata ft=new Fetchdata();
        ft.execute();

        make_pie_chart();

        mPieChart.startAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button3)
        {
            retreiveposts();
        }
    }

    class Fetchdata extends AsyncTask<Void,Void,Void> {
        ProgressDialog pgd = new ProgressDialog(Lavish.this);

        @Override
        protected void onPreExecute() {
            pgd.setMessage("Fetching Details");
            pgd.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pgd.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("aaa", "entered");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            final String[] next = {""};
            final ArrayList<String> ar = new ArrayList<String>();

            GraphRequest request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "me/picture?redirect=false&height=500", new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            // Insert your code here
                            Log.e("responsezzz", response.toString());
                            JSONObject jsonObject = response.getJSONObject();

                            try {
                                JSONObject jsa = jsonObject.getJSONObject("data");
                                Log.d("", jsa.toString());
//                                for (int i = 0; i < jsa.length(); i++)

                                Log.d("aaa:::", jsa.getString("url"));
                                ar.add(jsa.getString("url"));
                                var=jsa.getString("url");
                                abc(jsa.getString("url"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                    });
            request.executeAndWait();
            System.out.print("zx");

            System.out.print("zx");
            /*RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            for(int i=0;i<ar.size();i++) {
                System.out.print("xz");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://graph.facebook.com/" + ar.get(i) + "/picture?height=500&redirect=false", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("xxq", response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);*/
/*                        GraphRequest picturereq = GraphRequest.newGraphPathRequest(AccessToken.getCurrentAccessToken(), ar.get(i) + "/picture?height=500", new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {


                        try {
                            Log.d("xxx", response.toString());
                            JSONObject image_json = response.getJSONObject();
                            System.out.println(image_json.getJSONObject("data").getString("url"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                picturereq.executeAndWait();*/
//            }
        /*Volley Request*/
          /*  int flag=0;
            Log.d("aaa","Completed");
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            while(next[0]!=null) {

                //String url = next[0];
                StringRequest stringRequest = new StringRequest(Request.Method.GET,next[0], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("aaa", "Secondpage");


                        try {
                            Log.d("aa", response.toString());
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsa = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsa.length(); i++) {

                                    Log.d("aaa:::", jsa.getJSONObject(i).getString("id"));
                                    Log.d("aaa::", i + "::::" + jsa.length());

                            }
//                            if(!jsonObject.isNull("paging"))

                                Object aa = jsonObject.get("paging");
                                JSONObject a = new JSONObject(aa.toString());
                                if(a.getString("next")!=next[0]) {
                                    Log.d("aaa::", a.getString("next"));
                                    next[0]=a.getString("next");
                                    Log.d("fal",next[0]);
                                }else{
                                    next[0]=null;
                                    Log.d("qqw","aa");
                                }
//                                if (next[0] != "")
//                                    next[0] = a.getString("next");


                        } catch (JSONException e) {
//                            next[0]="";
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                if(next[0]!=null)
               queue.add(stringRequest);
            }*/
            return null;
        }

        private void abc(String url) {

            new HttpAsyncTask().execute();
//            HttpClient httpclient = HttpClients.createDefault();
//
//            try
//            {
////                URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");
////                URI uri = builder.build();
//                HttpPost request = new HttpPost("https://api.projectoxford.ai/emotion/v1.0/recognize");
//                request.setHeader("Content-Type", "application/json");
//                request.setHeader("Ocp-Apim-Subscription-Key", "98c5f335e32f4d10b735f92b61b9c371");
//
//
//                // Request body
//                StringEntity reqEntity = new StringEntity("{'url':'https://portalstoragewuprod2.azureedge.net/face/demo/detection 6 thumbnail.jpg'}");
//                request.setEntity(reqEntity);
//
//                HttpResponse response = httpclient.execute(request);
//                HttpEntity entity = response.getEntity();
//
//                if (entity != null)
//                {
//                    System.out.println(EntityUtils.toString(entity));
//                    Log.e("arrow",EntityUtils.toString(entity));
//                }
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.getMessage());
//            }
//        }
        }


    }

    public static String POST(){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost("https://api.projectoxford.ai/emotion/v1.0/recognize");

            String json = "";

            // 3. build jsonObject
            //String movie=movie_name.getText().toString();
            JSONObject jsonObject = new JSONObject();
            //jsonObject.accumulate("movie", movie);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            String x="{'url':\'"+var+"\'}";
            Log.e("err",x);
            StringEntity se = new StringEntity(x);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", "98c5f335e32f4d10b735f92b61b9c371");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String var="";
        @Override
        protected String doInBackground(String... urls) {



            var= POST();
            return var;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), var, Toast.LENGTH_LONG).show();
            try {
                JSONArray jarr=new JSONArray(var.toString());
                JSONObject job=jarr.getJSONObject(0);
                DecimalFormat dcf=new DecimalFormat("#");

                anger=(float)job.getJSONObject("scores").getDouble("anger");
                disgust=(float)job.getJSONObject("scores").getDouble("disgust");
                fear=(float)job.getJSONObject("scores").getDouble("fear");
                happpiness=(float)job.getJSONObject("scores").getDouble("happiness");
                neutral=(float)job.getJSONObject("scores").getDouble("neutral");
                sadness=(float)job.getJSONObject("scores").getDouble("sadness");
                surprise=(float)job.getJSONObject("scores").getDouble("surprise");



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static  String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }



}
