package com.example.android.subnetcalculator;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ipaddressAdapter extends ArrayAdapter<ipaddress> {

    int ipID;

    public ipaddressAdapter(Activity context, ArrayList<ipaddress> ip, int mIpID){
        super(context, 0, ip);
        this.ipID = mIpID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_a,parent,false);
        }


        ipaddress currentIP =  getItem(position);

        TextView la = listItemView.findViewById(R.id.list_A);
        la.setText(currentIP.getA());

        TextView lb = listItemView.findViewById(R.id.list_B);
        lb.setText(currentIP.getB());

        TextView lc = listItemView.findViewById(R.id.list_C);
        lc.setText(currentIP.getC());

        TextView ld = listItemView.findViewById(R.id.list_D);
        ld.setText(currentIP.getD());

        TextView le = listItemView.findViewById(R.id.list_E);
        le.setText(position+"");

        View textContainer = listItemView.findViewById(R.id.list_container);
        int ipa = ContextCompat.getColor(getContext(), ipID);
        if(position % 2 == 0) {
            textContainer.setBackgroundColor(ipa);
        }

        return listItemView;

    }


}
