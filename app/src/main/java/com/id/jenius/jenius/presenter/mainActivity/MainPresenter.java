package com.id.jenius.jenius.presenter.mainActivity;

import android.util.Log;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.network.JeniusNetwork;
import com.id.jenius.jenius.network.JeniusNetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    private MainViewInterface mainViewPresenter;
    private JeniusNetworkInterface api;

    public MainPresenter(MainViewInterface mainViewPresenter){
        this.mainViewPresenter = mainViewPresenter;
    }

    @Override
    public void getContacts() {
        getObservable().subscribeWith(getObserver());
    }

    @Override
    public void deleteContacts(String id){
        Log.d("adak",id);
        deleteObservableContacts(id).subscribeWith(getObserver());
    }

    public Observable<Contacts> getObservable(){

        api = JeniusNetwork.getRetrofit().create(JeniusNetworkInterface.class);

       return api.getAllData().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Contacts> deleteObservableContacts(String id){

        api = JeniusNetwork.getRetrofit().create(JeniusNetworkInterface.class);

        return api.deleteDataContacts(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Contacts> getObserver(){
        return new DisposableObserver<Contacts>() {
            @Override
            public void onNext(Contacts contacts) {
                mainViewPresenter.displayData(contacts);
            }

            @Override
            public void onError(Throwable e) {
                mainViewPresenter.displayError(e.toString());
            }

            @Override
            public void onComplete() {
                mainViewPresenter.hideProgressBar();
            }
        };
    }




}
