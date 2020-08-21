package com.krikax.prod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

   EditText phno;
   CountryCodePicker cpp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phno=findViewById(R.id.mobnum);
        cpp=findViewById(R.id.countrycode);
        cpp.registerCarrierNumberEditText(phno);

        findViewById(R.id.sendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = cpp.getFullNumberWithPlus();

                if (number.isEmpty() || number.length() < 10) {
                    phno.setError("Valid number is required");
                    phno.requestFocus();
                    return;
                }


                String phonenumber =  number ;

                Intent intent = new Intent(MainActivity.this, enableOtpActivity.class);
                intent.putExtra("phonenumber", phonenumber);

                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, Productselection.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
