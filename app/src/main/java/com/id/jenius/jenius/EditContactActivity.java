package com.id.jenius.jenius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.model.Data;
import com.id.jenius.jenius.presenter.editContacts.EditContactsPresenter;
import com.id.jenius.jenius.presenter.editContacts.EditContactsViewInterface;
import com.id.jenius.jenius.util.ProgressDialogBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditContactActivity extends AppCompatActivity implements EditContactsViewInterface {

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

    private EditContactsPresenter editContactsPresenter;
    private String id,firstname,lastname,photo;
    private int age;
    private ProgressDialogBar progressDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        ButterKnife.bind(this);
        editContactsPresenter = new EditContactsPresenter(this);
        progressDialogBar = new ProgressDialogBar();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getString("id");
        firstname = extras.getString("firstname");
        lastname = extras.getString("lastname");
        photo = extras.getString("photo");
        age = extras.getInt("age");

        firstNameEditText.setText(firstname);
        lastNameEditText.setText(lastname);
        ageEditText.setText(String.valueOf(age));
        photoEditText.setText(photo);
        buttonSubmit.setOnClickListener( e ->
                putContacts()
        );
    }

    @Override
    public void displayData(List<Contacts> contacts){
        Toast.makeText(this,contacts.get(0).getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayError(String e){
        Toast.makeText(this,e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(){
        progressDialogBar.ProgressDialogBar(this);
    }

    @Override
    public void hideProgressBar(){
        progressDialogBar.HideProgressDialogBar();
        startActivity(new Intent(EditContactActivity.this, MainActivity.class));
    }

    private void putContacts(){
        int ages = Integer.valueOf(ageEditText.getText().toString());
        List listA = new ArrayList();
        listA.add(firstNameEditText.getText().toString());
        listA.add(lastNameEditText.getText().toString());
        listA.add(ages);
        listA.add(photoEditText.getText().toString());

        Data data = new Data(firstNameEditText.getText().toString(),lastNameEditText.getText().toString(),ages,photoEditText.getText().toString());
        editContactsPresenter.putContacts(data,id);
    }
}
