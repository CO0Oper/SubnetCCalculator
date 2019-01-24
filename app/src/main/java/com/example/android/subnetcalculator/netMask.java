package com.example.android.subnetcalculator;

public class netMask {

    int nm;
    int q, w, e, r;
    static int subA;
    static int subB;
    static int subC;
    static int subD;
    int b;
    static hNaddress subM;

    public netMask(int num) {
        this.nm = num;
        toMask(num);
        this.q = subM.a;
        this.w = subM.b;
        this.e = subM.c;
        this.r = subM.d;

    }

    /**
     * Convert binary number to decimal.
     * @param num - input binary number.
     * @return
     */
    public int toDecimal(int num) {
        int b = 0;
        for(int i = 0; i < num; i++) {
            b = b + (int)Math.pow(2, 7-i);
        }
        return b;
    }

    public void toMask(int sm) {
        if(sm < 8) {
            q = toDecimal(sm);
            w = 0;
            e = 0;
            r = 0;
        }else if(sm == 8) {
            q = 255;
            w = 0;
            e = 0;
            r = 0;
        }else if(sm >= 9 && sm <= 16) {
            q = 255;
            w = toDecimal(sm - 8);
            e = 0;
            r = 0;
        }else if(sm >= 17 && sm <= 24) {
            q = 255;
            w = 255;
            e = toDecimal(sm-16);
            r = 0;
        }else {
            q = 255;
            w = 255;
            e = 255;
            r = toDecimal(sm-24);

        }
        subM = new hNaddress(q,w,e,r);

    }

    public static int countBits(int A, int B, int C, int D){

        subA = A;
        subB = B;
        subC = C;
        subD = D;
        int bits = 0;
        String a = subCal.convertToBinaryy(subA);
        String b = subCal.convertToBinaryy(subB);
        String c = subCal.convertToBinaryy(subC);
        String d = subCal.convertToBinaryy(subD);
        for(int i = 0; i < 8; i++) {
            if(a.charAt(i) == '1') {
                bits++;
            }
            if(b.charAt(i) == '1') {
                bits++;
            }
            if(c.charAt(i) == '1') {
                bits++;
            }
            if(d.charAt(i) == '1') {
                bits++;
            }
        }
        return bits;

    }

    public String printNetworkMask() {

        return (
                q + "." + w + "." +
                        e + "." + r);
    }

    public String printNetworkMaskbi() {
        return (
                subCal.convertToBinaryy(q) + "." +
                        subCal.convertToBinaryy(w) + "." +
                        subCal.convertToBinaryy(e) + "." +
                        subCal.convertToBinaryy(r));
    }
}
