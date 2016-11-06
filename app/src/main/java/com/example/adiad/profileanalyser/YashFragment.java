package com.example.adiad.profileanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adiad.profileanalyser.Health_Api.HeartRateMonitor;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by code_eagle on 11/5/2016.
 */
public class YashFragment extends Fragment implements View.OnClickListener{


    static View rootView;
    public static TextView heartbeat;
    public static ImageButton heartbutton;
    public static String value="0";
    public static HashMap<Double,Integer> hp;
    Button details;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_yash_health, container, false);
        hp=new HashMap<>();
        details=(Button)rootView.findViewById(R.id.detailsbutton);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Details.class);
                startActivity(i);
                onPause();
            }
        });
        heartbeat = (TextView)rootView.findViewById(R.id.heartbeat);
        heartbutton = (ImageButton)rootView.findViewById(R.id.heartbutton);
        heartbutton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), HeartRateMonitor.class);
        heartbutton.setVisibility(View.GONE);
        heartbeat.setVisibility(View.VISIBLE);
        startActivity(i);
        onPause();
    }

    public static void updateGraph()
    {
        GraphView graph = (GraphView)rootView. findViewById(R.id.graph);



        DataPoint[] dp=new DataPoint[10];
        dp[0]= new DataPoint(Calendar.HOUR,0);
        int i=1;
        Double a=0.0;
        int b=0;

        for(Map.Entry<Double,Integer> entry : hp.entrySet())
        {
            dp[i]= new DataPoint(entry.getKey(),entry.getValue());
            a=entry.getKey();
            b=entry.getValue();
            i++;
        }
        for(;i<dp.length;i++)
            dp[i]= new DataPoint(a,b);
        for(int j=0;j<dp.length;j++)
        {
            Log.e("point",dp[j].getX()+"   "+dp[j].getY());
        }
       LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        graph.addSeries(series);

    }
}
