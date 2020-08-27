package tfws.download.video.coronatrackerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelsList;
    private List<CountryModel> countryModelsListfiltered;
    public MyCustomAdapter(Context context, List<CountryModel> countryModelsList) {
        super(context, R.layout.list_custom_item,countryModelsList);

        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListfiltered = countryModelsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);
        tvCountryName.setText(countryModelsListfiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListfiltered.get(position).getFlag()).into(imageView);


        return view;
    }
    public int getCount(){

        return countryModelsListfiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelsListfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;
                }
                else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for(CountryModel itemsModel:countryModelsList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryModelsListfiltered = (List<CountryModel>) filterResults.values;
                affected.countryModelList = (List<CountryModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
