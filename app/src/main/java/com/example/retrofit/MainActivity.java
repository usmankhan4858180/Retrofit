package com.example.retrofit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";
    EditText fname;
    EditText lname;
    EditText foneNo;
    EditText Age;
    EditText EmailID;
    EditText Blood_Group;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        foneNo = findViewById(R.id.Fone);
        Age = findViewById(R.id.donor_age);
        EmailID = findViewById(R.id.MailID);
        Blood_Group = findViewById(R.id.BloodGroup);
        post = findViewById(R.id.SendData);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_first = fname.getText().toString().trim();
                String name_last = lname.getText().toString().trim();
                String telephone_no = foneNo.getText().toString();
                String age_donor = Age.getText().toString().trim();
                String id_email = EmailID.getText().toString().trim();
                String id_bloodgroup = Blood_Group.getText().toString().trim();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                DonorAPI donorAPI = retrofit.create(DonorAPI.class);
                Call<Donor> donorCall = donorAPI.saveDonor(name_first, name_last, Integer.parseInt(telephone_no),
                        Integer.parseInt(age_donor),
                        id_email, Integer.parseInt(id_bloodgroup));
                donorCall.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(Call<Donor> call, Response<Donor> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                    }

                    @Override
                    public void onFailure(Call<Donor> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }

        });

    }
}