package com.example.android.subnetcalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class addressb_fragment extends Fragment {

    ArrayList<ipaddress> ipss = new ArrayList<>();

    ListView listView2;
    ScrollView scrollView;

    public String ipinput, bitsinput;
    public hNaddress subAddress;
    public netMask netmask;
    public subCal sub;
    public int bits;

    boolean isEmpty;

    private Button button;

    EditText edit_one;
    EditText edit_two;

    TextView ipaddressTextView;
    TextView networkTextView;
    TextView broadcastTextView;
    TextView hostminTextView;
    TextView hostmaxTextView;
    TextView totalhostTextView;
    TextView numberofhostsTextView;
    TextView netmaskTextView;
    TextView wildcastTextView;
    TextView binarynetmaskTextView;
    TextView ipclassTextView;
    TextView cidrTextView;
    TextView possiableIndex;
    TextView errorA;
    TextView textViewA;
    TextView textViewB;
    TextView textViewC;
    TextView textViewD;
    TextView textViewE;



    /**
     * Required empty constructor for fragment.
     */
    public addressb_fragment() { }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.list_b, container, false);
        scrollView = rootView.findViewById(R.id.scrollable);

        listView2 = rootView.findViewById(R.id.hostlistsbb);

        textViewA = rootView.findViewById(R.id.list_nadd);

        textViewB = rootView.findViewById(R.id.list_hmin);

        textViewC = rootView.findViewById(R.id.list_hmax);

        textViewD = rootView.findViewById(R.id.list_badd);

        textViewE = rootView.findViewById(R.id.list_index);

        button = rootView.findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                       edit_one = rootView.findViewById(R.id.li_addressa);
                       edit_two = rootView.findViewById(R.id.li_subbits);

                           ipinput = edit_one.getText().toString();
                           bitsinput = edit_two.getText().toString();

                           String[] addresses;
                           int a = 0, b=0, c=0, d=0;
                           int bit2;

                       if(TextUtils.isEmpty(ipinput)){
                           errorMesssage(3);
                           edit_one.setError("Input can't be null");
                           return;
                       }else if(TextUtils.isEmpty(bitsinput)){
                           errorMesssage(3);
                           edit_two.setError("Input can't be null");
                           return;
                       }else{

                           addresses = ipinput.split("\\.");
                           bit2 = Integer.parseInt(bitsinput);
                           if(addresses.length<2){//addresses[0].isEmpty()||addresses[1].isEmpty()||addresses[2].isEmpty()||(addresses[3].isEmpty())
                               errorMesssage(2);
                               edit_one.setError("Input incomplete");
                               return;
                           }else {
                               a = Integer.parseInt(addresses[0]);
                           }
                           if(addresses.length<2){
                               errorMesssage(2);
                               edit_one.setError("Input incomplete");
                               return;
                           }else{
                               b = Integer.parseInt(addresses[1]);

                           }

                           if(addresses.length<3){
                               errorMesssage(2);
                               edit_one.setError("Input incomplete");
                               return;
                           }else{
                               c = Integer.parseInt(addresses[2]);

                           }

                           if(addresses.length<4){
                               errorMesssage(2);
                               edit_one.setError("Input incomplete");
                               return;
                           }else{
                               d = Integer.parseInt(addresses[3]);
                           }
                               if(a< 0 || b < 0 || c < 0|| d < 0|| a > 255 || b > 255|| c > 255|| d>255){
                               errorMesssage(2);
                               edit_one.setError("Input out of bounds");
                               return;
                           }else if(bit2 < 0 || bit2 > 30){
                               errorMesssage(1);
                               edit_two.setError("Input out of bounds");
                               return;
                           }
                           else{
                               isEmpty = false;
                               inputB();
                           }

                       }

                           int aaa  = getBits();
                           sub.possiblenetwork(aaa);
                           ipss = sub.outputAr();

                           errorA.setText("");

                           textViewA.setText("Network Address");
                           textViewB.setText("Usable Host Min");
                           textViewC.setText("Usable Host Max");
                           textViewD.setText("Broadcast Address");
                           textViewE.setText("Idx");

                           ipaddressAdapter adapter = new ipaddressAdapter(getActivity(), ipss, R.color.list_a);

                           listView2.setAdapter(adapter);
                           setListViewHeightBasedOnChildren(listView2);

                                      }
        });

        //Print the ip address.
        ipaddressTextView = rootView.findViewById(R.id.ld_address);

        //Print the network address
        networkTextView = rootView.findViewById(R.id.ld_network);

        //Print the broadcast
        broadcastTextView = rootView.findViewById(R.id.ld_broadcast);

        //Print the Host min
        hostminTextView = rootView.findViewById(R.id.ld_hostmin);

        //Print the Host max
        hostmaxTextView = rootView.findViewById(R.id.ld_hostmax);

        //Print total hosts
        totalhostTextView = rootView.findViewById(R.id.ld_totalhost);

        //Print number of hosts
        numberofhostsTextView = rootView.findViewById(R.id.ld_numberofhosts);

        //Print netmask
        netmaskTextView = rootView.findViewById(R.id.ld_netmask);

        //Print the wildcast address
        wildcastTextView = rootView.findViewById(R.id.ld_wildcast);

        //Print netmask in binary
        binarynetmaskTextView = rootView.findViewById(R.id.ld_binarynetmask);

        //Print class
        ipclassTextView = rootView.findViewById(R.id.ld_ipclass);

        //CIDR Notation
        cidrTextView = rootView.findViewById(R.id.ld_cirdnotation);

        //Possiable host address Index
        possiableIndex = rootView.findViewById(R.id.ld_possiableIndex);

        errorA = rootView.findViewById(R.id.hint_A);

        return rootView;


    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void inputB() throws NullPointerException, IndexOutOfBoundsException{
        int a, b, c, d;
        int sa, sb, sc, sd;

        //Getting ip address.
        String[] names = ipinput.split("\\.");

        a = Integer.parseInt(names[0]);
        b = Integer.parseInt(names[1]);
        c = Integer.parseInt(names[2]);
        d = Integer.parseInt(names[3]);

        if(a>255 || a<0 || b>255 || b<0 ||c>255 || c<0 ||d>255 || d<0 ) {
            //throw new IndexOutOfBoundsException();
            errorMesssage(2);
            throw new IndexOutOfBoundsException();
        }
        subAddress = new hNaddress(a, b, c, d);

        if(subAddress.equals(null)) {
            errorMesssage(3);
            throw new NullPointerException();
        }

        sa = Integer.parseInt(bitsinput);
        if(sa < 0 ) {
            errorMesssage(1);
            throw new IndexOutOfBoundsException();
        }else if(sa <= 30) {
            netmask = new netMask(sa);
        }else {
            String[] subnet = bitsinput.split("\\.");
            sa = Integer.parseInt(subnet[0]);
            sb = Integer.parseInt(subnet[1]);
            sc = Integer.parseInt(subnet[2]);
            sd = Integer.parseInt(subnet[3]);
            bits = netMask.countBits(sa, sb, sc, sd);
            netmask = new netMask(bits);
        }
        if(subAddress.equals(null) || netmask.equals(null)) {
            errorMesssage(3);
            throw new NullPointerException();
        }
        sub = new subCal(subAddress, netmask);
        bits = sa;

        outputB();
    }

    public void errorMesssage(int a){
        switch (a){
            case 1: //case of invalid subnet bits input
                errorA.setText("Invalid boundary for subnet bits");
            case 2: //case of ip address out of bound input
                errorA.setText("Invalid boundary for ip address");
            case 3: //case of null input
                errorA.setText("Input can not be null");
        }
    }

    public int getBits(){
        return bits;
    }

    public void outputB(){

        //Print the ip address.
        String s1 = subAddress.print();
        ipaddressTextView.setText(ipinput);

        //Print the network address
        String s2 = sub.getNetworkAddress();
        networkTextView.setText(s2);

        //Print the broadcast
        sub.getBroadCastAddress(bits);
        String s5 = sub.getBroadCastAddress(bits);
        broadcastTextView.setText(s5);

        //Print the Host min
        String s3 = sub.getHostMin();
        hostminTextView.setText(s3);

        //Print the Host max
        String s4 = sub.getHostMax();
        hostmaxTextView.setText(s4);

        //Print total hosts
        String s6 =  sub.totalHosts(bits) + "";
        totalhostTextView.setText(s6);

        //Print number of hosts
        String s7 = sub.numberofusableHosts(bits) + "";
        numberofhostsTextView.setText(s7);

        //Print netmask
        String s8 = netmask.printNetworkMask();
        netmaskTextView.setText(s8);

        //Print the wildcast
        String s12 = sub.getWildcard(bits);
        wildcastTextView.setText(s12);

        //Print netmask in binary
        String s9 = netmask.printNetworkMaskbi();
        binarynetmaskTextView.setText(s9);
        //inB.setText(s9);

        //Print class
        String s10 = sub.defineClass(bits);
        ipclassTextView.setText(s10);

        //CIDR Notation
        String s11 = bits + "";
        cidrTextView.setText(s11);

        sub.store(subAddress);
        String s13 = sub.subIndex(bits) + "";
        possiableIndex.setText(s13);

    }

}