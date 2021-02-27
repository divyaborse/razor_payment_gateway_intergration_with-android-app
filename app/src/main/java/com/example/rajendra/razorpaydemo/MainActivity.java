package com.example.rajendra.razorpaydemo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements PaymentResultListener {

    Button btPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btPay = findViewById(R.id.bt_pay);
        String sAmount = "100";
        final int amount = Math.round(Float.parseFloat(sAmount) * 100);
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_RFeXK90Qez9paB");
                checkout.setImage(R.drawable.rzp_logo);
                JSONObject object = new JSONObject();
                try {
                    object.put("name","Android coding");
                    //put description
                    object.put("description","Test Payment");
                    //put theme color
                    object.put("theme.color","#0093DD");
                    //put currency unit
                    object.put("currency","INR");
                    //put amount
                    object.put("amount",amount);
                    //put mobile no
                    object.put("profile.contact","8983403218");
                    //put email
                    object.put("profile.email","borsedivya301@gmail.com");
                    //open razorpay checkout activity
                    checkout.open(MainActivity.this,object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //initialize alter dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle("Payment ID");
         builder.setMessage(s);
        //show alert dialog
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
