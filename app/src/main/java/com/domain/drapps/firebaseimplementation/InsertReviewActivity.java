package com.domain.drapps.firebaseimplementation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by diogo.rosa on 20/03/2018.
 */

public class InsertReviewActivity extends AppCompatActivity{

    Button btnInsert;
    private DatabaseReference mDatabase;
    ServiceInterface serviceApi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_review);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnInsert = findViewById(R.id.btn_insert_new);
        serviceApi = MainApplication.getNewService();

        rx.Observable<PlacesResponse> observable = serviceApi.getPLaces(getString(R.string.api_key));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlacesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PlacesResponse response) {
                        Log.i("LIST", response.toString());

                        for(PlaceSingleResponse singlePlace : response.getResults()){
                            Log.i(singlePlace.id, singlePlace.name);
                        }
                    }
                });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Company company = new Company(FirebaseAuth.getInstance().getCurrentUser().getUid(),"Company1", "Is a simple company ");
                mDatabase.child("companies").child("values").push().setValue(company);


            }
        });

    }
}
