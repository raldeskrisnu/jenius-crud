package com.id.jenius.jenius.presenter.editContacts;

import com.id.jenius.jenius.model.Contacts;

import java.util.List;

public interface EditContactsViewInterface {

    void displayData(List<Contacts> contacts);
    void displayError(String str);
    void showProgressBar();
    void hideProgressBar();
}
