package tfws.download.video.coronatrackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView countryName,countryCases,countryActive,countryCritical,countryRecovered,countryTodayCases,countryTodayDeaths,countryDeaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);
        getSupportActionBar().setTitle("Details of "+affected.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        countryName = findViewById(R.id.tvCountryName);
        countryTodayCases = findViewById(R.id.tvCountryTodayCases);
        countryTodayDeaths = findViewById(R.id.tvCountryTodayDeaths);
        countryDeaths = findViewById(R.id.tvCountryDeaths);
        countryCritical = findViewById(R.id.tvCountryCritical);
        countryRecovered = findViewById(R.id.tvCountryRecovered);
        countryCases = findViewById(R.id.tvCountryCases);
        countryActive = findViewById(R.id.tvCountryActive);

        countryName.setText(affected.countryModelList.get(positionCountry).getCountry());
        countryTodayDeaths.setText(affected.countryModelList.get(positionCountry).getTodayDeaths());
        countryTodayCases.setText(affected.countryModelList.get(positionCountry).getTodayCases());
        countryCases.setText(affected.countryModelList.get(positionCountry).getCases());
        countryCritical.setText(affected.countryModelList.get(positionCountry).getCritical());
        countryRecovered.setText(affected.countryModelList.get(positionCountry).getRecovered());
        countryActive.setText(affected.countryModelList.get(positionCountry).getActive());
        countryDeaths.setText(affected.countryModelList.get(positionCountry).getDeaths());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
