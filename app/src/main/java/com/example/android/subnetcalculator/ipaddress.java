package com.example.android.subnetcalculator;

import java.util.ArrayList;

public class ipaddress {
    String a, b, c, d;
    private hNaddress l1, l2, l3, l4;


    public ipaddress(){

    }

    public ipaddress(String A, String B, String C, String D){
       this.a = A;
       this.b = B;
       this.c = C;
       this.d = D;
    }

    public ipaddress(hNaddress L1, hNaddress L2, hNaddress L3, hNaddress L4){
        this.l1 = L1;
        this.l2 = L2;
        this.l3 = L3;
        this.l4 = L4;
    }



    public String getA() { return a;}

    public String getB() { return b;}

    public String getC() { return c;}

    public String getD() { return d;}


}
