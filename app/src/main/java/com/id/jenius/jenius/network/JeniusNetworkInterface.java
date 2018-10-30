package com.id.jenius.jenius.network;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.model.Data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JeniusNetworkInterface {

    @GET("contact")
    Observable<Contacts>getAllData();

    @POST("contact")
    Single<Contacts> sendDataContacts(@Body Data data);

    @PUT("contact/{id}")
    Single<List<Contacts>>putDataContacts(@Body Data data, @Path("id") String id);

    @DELETE("contact/{id}")
    Observable<Contacts>deleteDataContacts(@Path("id") String id);
}
