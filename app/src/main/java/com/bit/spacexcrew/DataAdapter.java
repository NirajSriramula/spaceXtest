package com.bit.spacexcrew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Crew> crewList;
    private Activity context;
    private CrewDatabase db;

    public DataAdapter(List<Crew> crewList, Activity context) {
        this.crewList = crewList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_member,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Crew data = crewList.get(position);
        db = CrewDatabase.getInstance(context);
        holder.name.setText("Name : " + data.getName());
        holder.status.setText("Status : " + data.getStatus());
        holder.agency.setText("Agency : " + data.getAgency());
        holder.wiki.setClickable(true);
        holder.wiki.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href=" + data.getWikipedia() + ">Wikipedia  </a>";
        holder.wiki.setText(Html.fromHtml(text));
        Glide.with(context)
                .load(data.getImageURL())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,status,wiki,agency;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            status = view.findViewById(R.id.status);
            wiki = view.findViewById(R.id.wiki);
            agency = view.findViewById(R.id.agency);
            image = view.findViewById(R.id.dp);
        }
    }

}
