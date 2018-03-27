package com.domain.drapps.firebaseimplementation;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.common.api.Response;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by diogo.rosa on 21/03/2018.
 */

public interface ServiceInterface {

@GET("https://maps.googleapis.com/maps/api/place/textsearch/xml?query=restaurants+in+Sydney")
rx.Observable<PlacesResponse> getPLaces(@Query("key") String apiKey);
}
