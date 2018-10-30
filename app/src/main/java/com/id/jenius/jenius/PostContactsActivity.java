package com.id.jenius.jenius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.model.Data;
import com.id.jenius.jenius.presenter.postContacts.PostContactsPresenter;
import com.id.jenius.jenius.presenter.postContacts.PostContactsViewPresenter;
import com.id.jenius.jenius.util.ProgressDialogBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostContactsActivity extends AppCompatActivity implements PostContactsViewPresenter {

    @BindView(R.id.firstNameEditText)
    TextView firstNameEditText;

    @BindView(R.id.lastNameEditText)
    TextView lastNameEditText;

    @BindView(R.id.ageEditText)
    TextView ageEditText;

    @BindView(R.id.photoEditText)
    TextView photoEditText;

    @BindView(R.id.buttonSubmit)
    Button buttonSubmit;

    private PostContactsPresenter postContactsPresenter;
    private ProgressDialogBar progressDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postdata_activity);
        ButterKnife.bind(this);
        progressDialogBar = new ProgressDialogBar();
        buttonSubmit.setOnClickListener( e ->
                sendDataToServer()
        );
    }

    @Override
    public void response(Contacts contacts){
        Toast.makeText(this,contacts.getMessage(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(PostContactsActivity.this, MainActivity.class));
    }

    @Override
    public void showProgressBar(){
        progressDialogBar.ProgressDialogBar(this);
    }

    @Override
    public void hideProgressBar(){
        progressDialogBar.HideProgressDialogBar();
    }

    private void sendDataToServer(){
        if(firstNameEditText.getText().length() >= 3 && lastNameEditText.getText().length() >=3 && photoEditText.getText().length() >=3){
            int age = Integer.valueOf(ageEditText.getText().toString());
            Data data = new Data(firstNameEditText.getText().toString(),lastNameEditText.getText().toString(),age,photoEditText.getText().toString());
            postContactsPresenter = new PostContactsPresenter(this);
            postContactsPresenter.postContacts(data);
        } else {
            Toast.makeText(this,"minimum character is 3", Toast.LENGTH_LONG).show();
        }

    }
}
