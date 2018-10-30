package com.id.jenius.jenius.presenter.mainActivity;

import com.id.jenius.jenius.model.Contacts;

public interface MainViewInterface {

    void displayData(Contacts contacts);
    void displayError(String str);
    void hideProgressBar();
    void deleteData(String id);
}
