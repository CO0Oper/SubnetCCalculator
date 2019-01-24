package com.example.android.subnetcalculator;

public class hNaddress {

    int a, b, c, d;

    public hNaddress(int A, int B, int C, int D) {

        this.a = A;
        this.b = B;
        this.c = C;
        this.d = D;

    }
    /**
     * Print address in binary format.
     */
    public String print() {

        String s = (
                subCal.convertToBinaryy(a) + "." +
                        subCal.convertToBinaryy(b) + "." +
                        subCal.convertToBinaryy(c) + "." +
                        subCal.convertToBinaryy(d));
        return s;
    }

    public String printDecimal(){
        String s = (
                a + "." + b + "." + c + "." + d);
        return s;
    }

}
