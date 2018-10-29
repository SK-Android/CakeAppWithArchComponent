package com.androidapp.mcs.cakeappwitharchcomp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidapp.mcs.cakeappwitharchcomp.adapter.MyAdapter;
import com.androidapp.mcs.cakeappwitharchcomp.database.AppRepository;
import com.androidapp.mcs.cakeappwitharchcomp.database.CakeDatabase;
import com.androidapp.mcs.cakeappwitharchcomp.model.Cakes;
import com.androidapp.mcs.cakeappwitharchcomp.service.MyWebService;
import com.androidapp.mcs.cakeappwitharchcomp.viewmodel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    List<Cakes> cakesList;

    @BindView(R.id.main_rv)
    RecyclerView recyclerView;

    private MainViewModel mViewModel;
    private CakeDatabase mCakeDb;
    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        recyclerView = findViewById(R.id.main_rv);

        ButterKnife.bind(this);

        initRecyclerView();
        initViewModel();


        MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
        Call<List<Cakes>> call = myWebService.getCakeItems();

        call.enqueue(new Callback<List<Cakes>>() {
            @Override
            public void onResponse(Call<List<Cakes>> call, Response<List<Cakes>> response) {
                cakesList = response.body();
                Toast.makeText(MainActivity.this, "Recieved\t"+cakesList.size()+"\tCakes from Service", Toast.LENGTH_SHORT).show();
                initRecyclerView();

                //--
                for (int i = 0; i < cakesList.size(); i++) {
                    String cake_title = cakesList.get(i).getTitle();
                    String cake_Desc = cakesList.get(i).getDesc();
                    String cake_Image = cakesList.get(i).getImage();

                    Cakes mCake = new Cakes(cake_title, cake_Desc, cake_Image);
                    mViewModel.addSampleData(mCake);

                }
            }

            @Override
            public void onFailure(Call<List<Cakes>> call, Throwable t) {
                Log.d("onFailure: ",t.getMessage());
            }
        });

    }

    private void initViewModel() {

        final Observer<List<Cakes>> cakeObserver = new Observer<List<Cakes>>() {
            @Override
            public void onChanged(@Nullable List<Cakes> cakes) {

            }
        };
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

    }

    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        if(cakesList != null)
        {
            MyAdapter adapter = new MyAdapter(cakesList,this );
            recyclerView.setAdapter(adapter);
        }


    }
}
