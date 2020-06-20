package com.androidjson.insertdata_androidjsoncom;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class paymentgate extends AppCompatActivity {
    public static String tempnum = "";

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

        ////////////////////////////////////////
        HttpClient Client = new DefaultHttpClient();

        // Create URL string

        String URL = "ht";

        //Log.i("httpget", URL);

        try
        {
            String SetServerString = "";

            // Create Request to server and get response

            HttpGet httpget = new HttpGet(URL);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            SetServerString = Client.execute(httpget, responseHandler);

            // Show response on activity

           // content.setText(SetServerString);
        }
        catch(Exception ex)
        {
           // content.setText("Fail!");
        }
    }



    ///////////////////////////////////////



    }


