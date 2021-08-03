package com.pk.vadati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    TextView t1;
    TextToSpeech t;
    private Timer timer = new Timer();
    String distance="Calculating Distance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=findViewById(R.id.message);
        t=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.UK);
                }
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LoginUser();   //Your code here
            }
        }, 0, 1*10*1000);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        t.speak(distance, TextToSpeech.QUEUE_FLUSH, null);
        return super.onTouchEvent(event);


    }

    private void LoginUser() {


            StringRequest request=new StringRequest(Request.Method.POST, "http://vadati.scienceontheweb.net/android.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();
                    String[] words=response.split("split123split");
                    System.out.println(words[0]);
                    distance=words[1];

                    t1.setText(words[0]);
                    String toSpeak=words[0];
                    t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Login", "Error: " + error
                            + "\nStatus Code " + error.networkResponse.statusCode
                            + "\nResponse Data " + error.networkResponse.data
                            + "\nCause " + error.getCause()
                            + "\nmessage" + error.getMessage());

                }


            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("data","data");

                    return params;
                }
            };
            Volley.newRequestQueue(this).add(request);


        }
    public void onPause(){
        if(t !=null){
            t.stop();
            t.shutdown();
        }
        super.onPause();
    }

}