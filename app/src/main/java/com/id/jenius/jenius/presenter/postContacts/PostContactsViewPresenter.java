package com.id.jenius.jenius.presenter.postContacts;

import com.id.jenius.jenius.model.Contacts;

public interface PostContactsViewPresenter {

    void response(Contacts contacts);
    void showProgressBar();
    void hideProgressBar();
}
