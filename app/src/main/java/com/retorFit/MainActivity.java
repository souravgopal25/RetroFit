package com.retorFit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.retorFit.Model.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    /*
    *This app is created for the purpose of Education and Learning
    *
    * In this app i am trying to learn RetroFit
    * calling MOVIEDB api
    * For documentation
     * https://developers.themoviedb.org/3
    *
    *GET popular movie
    *
     */
    //https://api.themoviedb.org/3/movie/popular?api_key=c116a273a05858824b03df6c4cfcbf20&language=en-US&page=1


    public static int page=1;
   /* private static  final String=getRes*/
    public static String LANGUAGE="en-us";
    public static final String BASE_URL="https://api.themoviedb.org";
    public static String CATEGORY="popular";
    public static int PAGE=1;
    public static final String TAG=MainActivity.class.getSimpleName();
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.display);

        //CREATING RETRO FIT BUILDER
        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        ApiInterface myinterface =retrofit.create(ApiInterface.class);
        //GETTING API KEY FROM STRING RESOURCE
        String APIKEY=getResources().getString(R.string.apikey);


        Call<MovieResults> call=myinterface.listofMovies(CATEGORY,APIKEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results=response.body();
                List<MovieResults.ResultsBean> listofMovies= results.getResults();
                //TO CHECK
                Log.e(TAG,"NO OF MOVIES IS"+listofMovies.size());
                for(int i=0;i<listofMovies.size();i++){
                    MovieResults.ResultsBean s=listofMovies.get(i);
                    display.append(s.getTitle()+"\n\n");
                }


            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG,t.getMessage());



            }
        });


    }
}