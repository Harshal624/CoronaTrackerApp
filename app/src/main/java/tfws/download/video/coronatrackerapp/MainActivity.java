package tfws.download.video.coronatrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCases = (TextView)findViewById(R.id.tvCases);
        tvRecovered = (TextView)findViewById(R.id.tvRecovered);
        tvCritical = (TextView)findViewById(R.id.tvCritical);
        tvActive = (TextView)findViewById(R.id.tvActive);
        tvTodayCases = (TextView)findViewById(R.id.tvCasesToday);
        tvTotalDeaths = (TextView)findViewById(R.id.tvDeaths);
        tvTodayDeaths = (TextView)findViewById(R.id.tvTodayDeaths);
        tvAffCountries = (TextView)findViewById(R.id.tvAffCountry);
        fetchData();

    }


    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),affected.class));

    }




    public void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffCountries.setText(jsonObject.getString("affectedCountries"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



}
