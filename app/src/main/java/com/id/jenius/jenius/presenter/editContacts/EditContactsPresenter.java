package com.id.jenius.jenius.presenter.editContacts;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.model.Data;
import com.id.jenius.jenius.network.JeniusNetwork;
import com.id.jenius.jenius.network.JeniusNetworkInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EditContactsPresenter implements EditContactsPresenterInterface {

    private EditContactsViewInterface editContactsViewInterface;
    private JeniusNetworkInterface networkInterface;
    private JeniusNetwork jeniusNetwork = new JeniusNetwork();
    private CompositeDisposable disposable = new CompositeDisposable();

    public EditContactsPresenter(EditContactsViewInterface editContactsViewInterface){
        this.editContactsViewInterface = editContactsViewInterface;
    }

    @Override
    public void putContacts(Data data, String id){
        networkInterface = jeniusNetwork.getRetrofit().create(JeniusNetworkInterface.class);
        disposable.add(
                networkInterface.putDataContacts(data,id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Contacts>>() {
                            //first call of disposableSingleObserver
                            @Override
                            public void onStart() {
                                editContactsViewInterface.showProgressBar();
                            }
                            //if success call onSuccess method
                            @Override
                            public void onSuccess(List<Contacts> contacts) {
                                editContactsViewInterface.displayData(contacts);
                                editContactsViewInterface.hideProgressBar();
                            }
                            //if failed call onError method
                            @Override
                            public void onError(Throwable e) {
                                editContactsViewInterface.hideProgressBar();
                            }
                        })
        );
    }
}
