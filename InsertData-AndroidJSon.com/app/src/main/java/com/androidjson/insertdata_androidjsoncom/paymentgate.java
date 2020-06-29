package com.androidjson.insertdata_androidjsoncom;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class paymentgate extends AppCompatActivity {
    public static String tempnum = "";
    String tid = journeyplan.ticketdata;
    public static DateFormat df = new SimpleDateFormat("dd:MM:yy HH:mm");
    public static String date1 = df.format(Calendar.getInstance().getTime());
    String src = journeyplan.fromadd;
    String dest = journeyplan.toadd;
    TextView tcost,wallet;
    String walletbalance="500";
    EditText mobilenum;
    Button paybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgate);
        final String paymenttobemade = getIntent().getStringExtra("paymentfromjourney");
       tcost=(TextView) findViewById(R.id.textView10);

       tcost.setText(paymenttobemade);

       wallet=(TextView) findViewById(R.id.wallettextview);
       wallet.setText(walletbalance);



        paybutton = (Button) findViewById(R.id.paymentbutton);

        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int cost = Integer.valueOf(paymenttobemade);
//                int wallet = Integer.valueOf(walletbalance);
//                int balance = wallet - cost;
                int balance=1;
                Intent myInt = new Intent(paymentgate.this,ticketshow.class);
                startActivity(myInt);
                getdetails();
                Toast.makeText(getApplicationContext(), tempnum, Toast.LENGTH_LONG).show();
                System.out.println("al n is " + tempnum);
            }
        });

    }

    private void getdetails() {
        mobilenum= (EditText)findViewById(R.id.phnum);
        tempnum = mobilenum.getText().toString();

        ////////////////////////////////////////SMS  API
//
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.105:8080/?number="+tempnum+"&smsbody=Your ticket booking is " +
                "confirmed on date "+date1+" from "+src+" to "+dest+
                " and is valid for 30mins. Have a safe Journey!!";
        Log.i("httpget", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);

    }



    ///////////////////////////////////////



    }


