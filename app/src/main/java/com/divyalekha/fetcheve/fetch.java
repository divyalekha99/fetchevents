package com.divyalekha.fetcheve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fetch extends AppCompatActivity implements AdapterView.OnItemClickListener {
   // public EditText mobileNo;
   ArrayList<String> navArray;
    ListView navlist;
    private Button button;
   // String event_list[]= new String[20];
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
        ListView navlist = (ListView)findViewById(R.id.navlist);

        navArray = new ArrayList<String>();

        EditText mobileNo= (EditText) findViewById(R.id.mobileNo);
        final String mob= mobileNo.getText().toString();





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
                    //for(int i=1 ;i<=11 ;i++) {
                        int i=1;
                        if (e1 == 1) {
                            //event_list[i] = "name1";
                            navArray.add("event1");
                            //i++;
                        }
                        if(e2 == 1)
                        {
                           //event_list[i] ="name2";
                            navArray.add("event1");
                           //i++;
                        }
                        if (e3 == 1) {
                           // event_list[i] = "name3";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e4 == 1) {
                            //event_list[i] = "name4";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e5 == 1) {
                            //event_list[i] = "name5";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e6 == 1) {
                            //event_list[i] = "name6";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e7 == 1) {
                            //event_list[i] = "name7";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e8 == 1) {
                            //event_list[i] = "name8";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e9 == 1) {
                            //event_list[i] = "name9";
                            navArray.add("event1");
                           // i++;
                        }
                        if (e10 == 1) {
                            //event_list[i] = "name10";
                            navArray.add("event1");
                            //i++;
                        }
                        if (e11 == 1) {
                           // event_list[i] = "name11";
                            navArray.add("event1");
                           // i++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fetch.this, android.R.layout.simple_list_item_1, navArray);
                        navlist.setAdapter(adapter);
                        navlist.setOnItemClickListener(this);







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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

