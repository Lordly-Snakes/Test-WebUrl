package com.antonyproject.pokypkinet.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.antonyproject.pokypkinet.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.antonyproject.pokypkinet.utils.NetworkUtils.generateURL;
import static com.antonyproject.pokypkinet.utils.NetworkUtils.getResponseFromUrl;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView t;
    ProgressBar bar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        t = root.findViewById(R.id.text_home);
        bar = root.findViewById(R.id.progressBar);
        RecyclerView vv= root.findViewById(R.id.res);

        //Button btn = root.findViewById(R.id.button);
        //btn.setOnClickListener(cl);
        VKQ b = new VKQ();
        b.execute(generateURL("users",1));
        //vv.addView(new TextView(getContext()).setText();

        return root;
    }



    class VKQ extends AsyncTask<URL,Integer,String>{
        public List<String> phones = new ArrayList<>();
        @Override
        protected String doInBackground(URL... urls) {

            String s="";
            try {
                s =getResponseFromUrl(urls[0]);
            }catch (IOException e){
                s=e.getMessage();
                e.printStackTrace();
            }finally {
                return s;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            bar.setVisibility(View.INVISIBLE);
            String[] array = s.split(";");
            s="";

            for(int i=0;i<array.length;i++){
                //phones.add(array[i]);
                s+=array[i]+";\n";
            }
            t.setText(s);
        }

        @Override
        protected void onPreExecute() {
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }
}


/*class mThread extends Thread{
    public String s1;
    public void run(){
        String s="";
        try {
            String r =generateURL("antohaproplus").toString();
            Log.d("ddd4",r);
            s =getResponseFromUrl(generateURL("antohaproplus"));
        }catch (IOException e){
            s=e.getMessage();
            e.printStackTrace();
        }finally {
            s1=s;
            Log.d("ddd3",s);
        }
    }
}

class Runn implements Runnable{

    @Override
    public void run() {
        String s="";
        try {
            String r =generateURL("antohaproplus").toString();
            Log.d("ddd1",r);
            s =getResponseFromUrl(generateURL("antohaproplus"));
        }catch (IOException e){
            s=e.getMessage();
            e.printStackTrace();
        }finally {

            Log.d("ddd2",s);
        }
    }
}*/