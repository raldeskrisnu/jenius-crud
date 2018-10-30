package com.id.jenius.jenius.presenter.postContacts;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.model.Data;
import com.id.jenius.jenius.network.JeniusNetwork;
import com.id.jenius.jenius.network.JeniusNetworkInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PostContactsPresenter implements PostContactsPresenterInterface {

    private PostContactsViewPresenter postContactsViewPresenter;
    private JeniusNetworkInterface networkInterface;
    private JeniusNetwork jeniusNetwork = new JeniusNetwork();
    private CompositeDisposable disposable = new CompositeDisposable();

    public PostContactsPresenter(PostContactsViewPresenter postContactsViewPresenter){
        this.postContactsViewPresenter = postContactsViewPresenter;
    }

    @Override
    public void postContacts(Data data){
        networkInterface = jeniusNetwork.getRetrofit().create(JeniusNetworkInterface.class);
        disposable.add(
                networkInterface.sendDataContacts(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Contacts>() {
                            //first call of disposableSingleObserver
                            @Override
                            public void onStart() {
                                postContactsViewPresenter.showProgressBar();
                            }
                            //if success call onSuccess method
                            @Override
                            public void onSuccess(Contacts contacts) {
                                postContactsViewPresenter.response(contacts);
                                postContactsViewPresenter.hideProgressBar();
                            }
                            //if failed call onError method
                            @Override
                            public void onError(Throwable e) {
                                
                            }
                        })
        );
    }


}
