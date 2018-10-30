package com.id.jenius.jenius.recylerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.id.jenius.jenius.EditContactActivity;
import com.id.jenius.jenius.MainActivity;
import com.id.jenius.jenius.R;
import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.presenter.mainActivity.MainViewInterface;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecylerViewHolder>  {

    private Contacts contactsList;
    private Context context;
    private Intent intents;
    private MainViewInterface mainViewPresenter;

    public RecyclerViewAdapter(Contacts contactsList, Context context, MainViewInterface mainViewInterface) {
        this.contactsList = contactsList;
        this.context = context;
        this.mainViewPresenter = mainViewInterface;
    }

    @NonNull
    @Override
    public RecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_main,parent,false);
        RecylerViewHolder rv = new RecylerViewHolder(v);

        return rv;
    }

    @Override
    public void onBindViewHolder(RecylerViewHolder holder, int position) {

        holder.ivName.setText(contactsList.getData().get(position).getFirstname() +" "+ contactsList.getData().get(position).getLastName());
        holder.ivAge.setText(String.valueOf(contactsList.getData().get(position).getAge()));
        Glide.with(context).load(contactsList.getData().get(position).getPhoto()).into(holder.ivPhoto);

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                popup.inflate(R.menu.recylerview_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                intents = new Intent(context, EditContactActivity.class);
                                intents.putExtra("id",contactsList.getData().get(position).getId());
                                intents.putExtra("firstname",contactsList.getData().get(position).getFirstname());
                                intents.putExtra("lastname",contactsList.getData().get(position).getLastName());
                                intents.putExtra("age",contactsList.getData().get(position).getAge());
                                intents.putExtra("photo",contactsList.getData().get(position).getPhoto());
                                context.startActivity(intents);
                                //handle menu1 click
                                break;
                            case R.id.delete:
                                mainViewPresenter.deleteData(contactsList.getData().get(position).getId());
                                //handle menu2 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
//        if(contactsList.getData().size() != 0){
            return contactsList.getData().size();
//        } else {
//            return 0;
//        }
    }

}
