package com.divyalekha.fetcheve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class fetch extends AppCompatActivity {
   // public EditText mobileNo;
    private Button button;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        try {
            if (!isConnected()) {
                Toast.makeText(this, "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException f) {

        } catch (IOException e) {

        }

        EditText mobileNo= (EditText) findViewById(R.id.mobileNo);
        final String mob= mobileNo.getText().toString();

        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchevents(mob);
            }
        });




    }

    private void fetchevents(final String mobileNo){
        String tag_string_req = "req_register";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "https://192.168.1.7:3306/and_con/fetch.php", new Response.Listener<String>() {

                @Override
                public void onResponse (String response){
                Log.d(TAG, "Register Response: " + response.toString());
                // hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        JSONObject events = jObj.getJSONObject("events");
                        int e1= events.getInt("e1");
                        int e2= events.getInt("e2");
                        int e3= events.getInt("e3");
                        int e4= events.getInt("e4");
                        int e5= events.getInt("e5");
                        int e6= events.getInt("e6");
                        int e7= events.getInt("e7");
                        int e8= events.getInt("e8");
                        int e9= events.getInt("e9");
                        int e10= events.getInt("e10");
                        int e11= events.getInt("e11");
                        Toast.makeText(getApplicationContext(), "fetched", Toast.LENGTH_LONG).show();

                     /*   Intent i = new Intent(signup.this, MainActivity.class);
                        i.putExtra("username", name);
                        i.putExtra("usermail", email);
                        i.putExtra("usernum", mobile);
                        startActivity(i);
                        finish();
                    */
                    } else {


                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error_volley " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobileNo", mobileNo);
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

}

