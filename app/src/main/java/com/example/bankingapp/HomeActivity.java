package com.example.bankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements PaymentResultListener {
      Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String sAmount="2500" ;

        final int amount=Math.round(Float.parseFloat(sAmount)*100);


        btn=findViewById(R.id.paybtn);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Checkout checkout=new Checkout();
              checkout.setKeyID("rzp_test_oIKEsCF0zSRsM1");
              checkout.setImage(R.drawable.preview);
              JSONObject object=new JSONObject();
              try {
                  object.put("name","Prashant");

                  object.put("description","Test Payment");

                  object.put("theme.color","#0093DD");

                  object.put("currency","INR");

                  object.put("amount",amount);

                  object.put("prefill.contact","99995555222");

                  object.put("prefill.email","prashantkumarjnv6@gmail.com");

                  checkout.open(HomeActivity.this,object);

              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      });


    }
    @Override
    public void onBackPressed(){
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("PAYMENT SUCCESSFUL");
        //ImageView img=new ImageView(R.drawable.check_mark);
        builder.setIcon(R.drawable.check_mark);
        builder.setMessage("payment ID:");
        builder.setMessage(s);
       builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
