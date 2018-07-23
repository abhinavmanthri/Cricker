package com.example.abhinav.cricker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Abhinav on 24-02-2018.
 */

public class BatsmenViewAdaptor extends ArrayAdapter<BatsmenBean>{

    public BatsmenViewAdaptor(Activity activity, ArrayList<BatsmenBean> batsmenList)
    {
        super(activity,0,batsmenList);

    }
    public View getView(int position, View convertView, ViewGroup parent){
        View currentView=convertView;
        if(currentView==null){
            currentView= LayoutInflater.from(getContext()).inflate(R.layout.activity_new_match,parent,false);
        }
        BatsmenBean obj=getItem(position);
        TextView name=(TextView)currentView.findViewById(R.id.name);
        name.setText(obj.getName());

        TextView runs=(TextView)currentView.findViewById(R.id.runss);
        runs.setText(obj.getRuns());
        TextView balls=(TextView)currentView.findViewById(R.id.balls);
        balls.setText(obj.getBalls());
        TextView fours=(TextView)currentView.findViewById(R.id.fours);
        fours.setText(obj.getFours());
        TextView sixes=(TextView)currentView.findViewById(R.id.sixes);
        sixes.setText(obj.getSixes());
        TextView strikeRate=(TextView)currentView.findViewById(R.id.strikeRate);
        strikeRate.setText(obj.getStrikeRate()+"");




        View containerText=currentView.findViewById(R.id.table_container);

        return currentView;
    }
}
