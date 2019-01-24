package com.example.android.subnetcalculator;

import java.util.ArrayList;

public class subCal {

    private hNaddress hN1, hN2, hN3;

    private netMask net1;

    hNaddress network;
    hNaddress broadcast;
    hNaddress hostmin;
    hNaddress hostmax;

    hNaddress possiableNet, possiablemin, possiablemax, possiablebroadcast;
    hNaddress wildcast, wildcastBroadcast;

    hNaddress ipaddress;
    int countt = 0;

    int indexx = 0;

    int bitss;

    public ArrayList<ipaddress> ipsss = new ArrayList<>();

    public subCal(hNaddress hN, netMask net) {
        this.hN1 = hN;
        this.net1 = net;
    }

    public subCal(hNaddress hN1, hNaddress hN2) {
        this.hN2 = hN1;
        this.hN3 = hN2;
    }


    /**
     * Method convert input Decimal number to binary format.
     * @param no, input decimal number.
     * @return return a binary number.
     */
    public static String convertToBinaryy(int no){

        String s = "";
        int container[] = new int[9];
        int i = 0;
        while (no > 0){
            container[i] = no%2;
            i++;
            no = no/2;
        }

        for (int j = i -1 ; j >= 0 ; j--){
            s = s + (container[j]);
        }

        if(s.equals("") ) {
            return "00000000";
        }

        if(s.length() < 8) {
            String ss = "";
            for(int k = 0 ; k < (8-s.length()); k ++) {
                ss = ss + "0";
            }
            s = ss + s;
        }
        return s;
    }

    public void store(hNaddress a) {
        this.ipaddress = a;
    }


    public void netWordDefine() {
        int subnetA = 0;
        int subnetB = 0;
        int subnetC = 0;
        int subnetD = 0;
        String a = subCal.convertToBinaryy(hN1.a);
        String b = subCal.convertToBinaryy(hN1.b);
        String c = subCal.convertToBinaryy(hN1.c);
        String d = subCal.convertToBinaryy(hN1.d);
        String aa = subCal.convertToBinaryy(net1.q);
        String bb = subCal.convertToBinaryy(net1.w);
        String cc = subCal.convertToBinaryy(net1.e);
        String dd = subCal.convertToBinaryy(net1.r);

        for(int i = 0; i < 8; i++) {
            if(a.charAt(i) == '1' && aa.charAt(i) =='1') {
                subnetA = subnetA + (int)Math.pow(2, 7-i);
            }
            if(b.charAt(i) == '1' && bb.charAt(i) =='1') {
                subnetB = subnetB + (int)Math.pow(2, 7-i);
            }
            if(c.charAt(i) == '1' && cc.charAt(i) =='1') {
                subnetC = subnetC + (int)Math.pow(2, 7-i);
            }
            if(d.charAt(i) == '1' && dd.charAt(i) =='1') {
                subnetD = subnetD + (int)Math.pow(2, 7-i);
            }
        }
        network = new hNaddress(subnetA, subnetB, subnetC, subnetD);
    }

    public void broadcastDefine(int bits) {
        if(bits < 8) {
            String s = subCal.convertToBinaryy(hN1.a);
            int subnet = 0;
            for (int j = 0; j < bits; j++) {
                if (s.charAt(j) == '1') {
                    subnet = subnet + (int) Math.pow(2, 7 - j);
                }
            }
            for (int i = bits; i < 8; i++) {
                subnet = subnet + (int) Math.pow(2, 7 - i);
            }
            broadcast = new hNaddress(subnet, 255, 255, 255);
        }else if(bits == 8) {
            broadcast = new hNaddress(network.a, 255, 255, 255);

        }else if(bits > 8 && bits <= 16) {
            String s = subCal.convertToBinaryy(hN1.b);
            int subnet = 0;
            for(int j = 0; j < bits-8; j++) {
                if(s.charAt(j)=='1') {
                    subnet = subnet + (int)Math.pow(2, 7-j);
                }
            }
            for(int i = bits-8; i < 8; i++) {
                subnet = subnet + (int)Math.pow(2, 7-i);
            }
            broadcast = new hNaddress(network.a, subnet, 255, 255);

        }else if(bits > 16 && bits <= 24) {
            String s = subCal.convertToBinaryy(hN1.c);
            int subnet = 0;
            for(int j = 0; j < bits-16; j++) {
                if(s.charAt(j)=='1') {
                    subnet = subnet + (int)Math.pow(2, 7-j);
                }
            }
            for(int i = bits-16; i < 8; i++) {
                subnet = subnet + (int)Math.pow(2, 7-i);
            }

            broadcast = new hNaddress(network.a, network.b, subnet, 255);
        }else if(bits > 24 && bits <= 30) {
            String s = subCal.convertToBinaryy(hN1.d);
            int subnet = 0;
            for(int j = 0; j < bits-24; j++) {
                if(s.charAt(j)=='1') {
                    subnet = subnet + (int)Math.pow(2, 7-j);
                }
            }
            for(int i = bits-24; i < 8; i++) {

                subnet = subnet + (int)Math.pow(2, 7-i);

            }
            broadcast = new hNaddress(network.a, network.b, network.c, subnet);
        }

    }

    public void hostMinDefine() {
        int subnetA = network.a;
        int subnetB = network.b;
        int subnetC = network.c;
        int subnetD = network.d+1;
        hostmin = new hNaddress(subnetA, subnetB, subnetC, subnetD);
    }

    public void hostMaxDefine() {
        int subnetA = broadcast.a;
        int subnetB = broadcast.b;
        int subnetC = broadcast.c;
        int subnetD = broadcast.d-1;
        hostmax = new hNaddress(subnetA, subnetB, subnetC, subnetD);
    }


    public int totalHosts(int bits) {
        return (int)Math.pow(2, 32-bits);
    }

    public int numberofusableHosts(int bits) {
        return (int)Math.pow(2, 32-bits) -2;
    }

    public String defineClass(int bits) {
        if(bits < 16) {
            return "A";
        }else if(bits < 24) {
            return "B";
        }else {
            return "C";
        }

    }

    public String getNetworkAddress() {
        netWordDefine();
        return network.printDecimal();
    }
    public String getBroadCastAddress(int bits) {
        broadcastDefine(bits);
        return broadcast.printDecimal();
    }

    public String getHostMin() {
        hostMinDefine();
        return hostmin.printDecimal();
    }

    public String getHostMax() {
        hostMaxDefine();
        return hostmax.printDecimal();
    }

    public int subIndex(int bits) {
        int numss = 0;

        if(bits == 8 || bits == 16 || bits == 24 || bits == 32) {

        }else if(bits < 8) {
            while(numss < 255) {

                numss = numss + (int) (Math.pow(2, 8-bits));

                if(numss < ipaddress.a) {
                    countt++;
                }else {
                    indexx = countt;
                }

            }
        }else if(bits < 16) {
            while(numss < 255) {

                numss = numss + (int) (Math.pow(2, 16-bits));

                if(numss < ipaddress.b) {
                    countt++;
                }else {
                    indexx = countt;
                }
            }

        }else if(bits < 24) {
            while(numss < 255) {
                numss = numss + (int) (Math.pow(2, 24-bits));
                if(numss < ipaddress.c) {
                    countt++;
                }else {
                    indexx = countt ;
                }
            }

        }else if(bits < 32) {
            while(numss < 255) {
                numss = numss + (int) (Math.pow(2, 32-bits));
                if(numss < ipaddress.d) {
                    countt++;
                }else {
                    indexx = countt;
                }

            }
        }

        return indexx;
    }

    public String getWildcard(int bits) {
        wildcastBroadcast = broadcast;
        if(bits < 8) {
            wildcast = new hNaddress(255-net1.q,255,255,255);
        }else if(bits == 8) {
            wildcast = new hNaddress(0, 255, 255, 255);
        }else if(bits < 16) {
            wildcast = new hNaddress(0,255-net1.w, 255, 255);
        }else if(bits == 16) {
            wildcast = new hNaddress(0, 0, 255, 255);
        }else if(bits < 24) {
            wildcast = new hNaddress(0, 0, 255-net1.e, 255);
        }else if(bits == 24) {
            wildcast = new hNaddress(0, 0, 0, 255);
        }else if(bits < 32) {
            wildcast = new hNaddress(0, 0, 0, 255-net1.r);
        }else if(bits == 32) {
            wildcast = new hNaddress(0, 0, 0, 0);
        }
        return wildcast.printDecimal();
    }

    public void possiblenetwork(int bits) {

        int nums = 0;

        if(bits == 8 || bits == 16 || bits == 24 || bits == 32) {

        }else if(bits < 8) {

            while(nums < 255) {
                possiableNet = new hNaddress(nums, 0 , 0, 0);
                possiablemin = new hNaddress(nums, possiableNet.b,possiableNet.c , 1);

                possiablebroadcast = new hNaddress(wildcast.a + nums, wildcast.b, wildcast.c, wildcast.d);
                possiablemax = new hNaddress(possiablebroadcast.a, possiablebroadcast.b,
                        possiablebroadcast.c, possiablebroadcast.d-1);
                receiveAr(possiableNet, possiablemin, possiablebroadcast, possiablemax);
                nums = nums + (int) (Math.pow(2, 8-bits));

            }
        }else if(bits < 16) {
            possiableNet = new hNaddress(hN1.a, 0, 0, 0);
            while(nums < 255) {
                possiableNet = new hNaddress(hN1.a, nums, 0, 0);
                possiablemin = new hNaddress(possiableNet.a, nums, 0, 1);
                possiablebroadcast = new hNaddress(possiableNet.a, wildcast.b+nums, 255, 255);
                possiablemax = new hNaddress(possiablebroadcast.a, possiablebroadcast.b,
                        possiablebroadcast.c, possiablebroadcast.d-1);
                receiveAr(possiableNet, possiablemin, possiablebroadcast, possiablemax);
                nums = nums + (int) (Math.pow(2, 16-bits));

            }

        }else if(bits < 24) {
            possiableNet = new hNaddress(hN1.a, hN1.b, 0, 0);
            while(nums < 255) {
                possiableNet = new hNaddress(hN1.a, hN1.b , nums, 0);
                possiablemin = new hNaddress(possiableNet.a, possiableNet.b, nums, 1);
                possiablebroadcast = new hNaddress(possiableNet.a, possiableNet.b, wildcast.c+nums, 255);
                possiablemax = new hNaddress(possiablebroadcast.a, possiablebroadcast.b,
                        possiablebroadcast.c, possiablebroadcast.d-1);
                receiveAr(possiableNet, possiablemin, possiablebroadcast, possiablemax);
                nums = nums + (int) (Math.pow(2, 24-bits));
            }

        }else if(bits < 32) {
            possiableNet = new hNaddress(hN1.a, hN1.b, hN1.c, 0);
            while(nums < 255) {
                possiableNet = new hNaddress(hN1.a, hN1.b , hN1.c, nums);
                possiablemin = new hNaddress(possiableNet.a, possiableNet.b, possiableNet.c, nums+1);
                possiablebroadcast = new hNaddress(possiableNet.a, possiableNet.b, possiableNet.c, wildcast.d+nums);
                possiablemax = new hNaddress(possiablebroadcast.a, possiablebroadcast.b,
                        possiablebroadcast.c, possiablebroadcast.d-1);
                receiveAr(possiableNet, possiablemin, possiablebroadcast, possiablemax);
                nums = nums + (int) (Math.pow(2, 32-bits));
            }
        }
    }

    public void receiveAr(hNaddress possiableNet, hNaddress possiablemin, hNaddress possiablemax, hNaddress possiablebroadcast){
        String a = possiableNet.printDecimal();
        String b = possiablemin.printDecimal();
        String c = possiablebroadcast.printDecimal();
        String d = possiablemax.printDecimal();
        ipsss.add(new ipaddress(a, b, c, d));

    }

    public ArrayList<ipaddress> outputAr(){
        return ipsss;
    }

}

