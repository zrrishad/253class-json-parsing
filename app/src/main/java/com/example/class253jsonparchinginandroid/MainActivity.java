package com.example.class253jsonparchinginandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText edNumber,edName,edEmail;
    Button bLoad;
    ProgressBar prog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        edEmail=findViewById(R.id.edEmail);
        edName=findViewById(R.id.edName);
        edNumber=findViewById(R.id.edNumber);
        bLoad=findViewById(R.id.bLoad);
        prog=findViewById(R.id.prog);


        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prog.setVisibility(View.VISIBLE);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://www.google.com";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ris71.000webhostapp.com/atiya/info.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                              prog.setVisibility(View.GONE);

                                Log.d("serverres", response);

                                try {
                                    JSONObject jsonObject= new JSONObject(response);
                                    String name=jsonObject.getString("name");
                                    String mobile=jsonObject.getString("mobile");
                                    String gmail=jsonObject.getString("gmail");

                                    edName.setText(name);
                                    edEmail.setText(gmail);
                                    edNumber.setText(mobile);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        bLoad.setText("That didn't work!");
                        prog.setVisibility(View.GONE);
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);









            }
        });








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}