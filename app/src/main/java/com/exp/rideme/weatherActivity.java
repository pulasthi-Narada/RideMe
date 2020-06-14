package com.exp.rideme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class weatherActivity extends AppCompatActivity {
    TextView lblCity,lblDescription,lblHumidity,lblTemperature,lblWind;
     Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        lblCity = findViewById(R.id.lblCity);
        lblDescription = findViewById(R.id.lblDescription);
        lblHumidity = findViewById(R.id.lblHumidity);
        lblTemperature = findViewById(R.id.lblTemperature);
        lblWind = findViewById(R.id.lblWind);
         btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFromServer();
            }
        });
    }

    private void loadDataFromServer() {

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://api.openweathermap.org/data/2.5/weather?q=Colombo&appid=66b7af658bb0f439a3a9901e9bcaceef";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.e("Response" , response);
                try
                {
                    JSONObject WeatherDataObject = new JSONObject(response); // created JSON object to get Data
                    JSONArray weatherArray = WeatherDataObject.getJSONArray("weather"); //created JSON Array to get data
                    lblCity.setText(WeatherDataObject.getString("name"));
                    lblDescription.setText(weatherArray.getJSONObject(0).getString("description"));
                    lblHumidity.setText(WeatherDataObject.getJSONObject("main").getString("humidity"));
                    lblTemperature.setText(WeatherDataObject.getJSONObject("main").getString("temp"));
                    lblWind.setText(WeatherDataObject.getJSONObject("wind").getString("speed"));
                    //Log.e("JSON Object Name",WeatherDataObject.getString("name"));
                }
                catch(JSONException ex)
                {

                }
                pDialog.dismissWithAnimation();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.dismissWithAnimation();
            }
        });
        queue.add(request);
    }
}