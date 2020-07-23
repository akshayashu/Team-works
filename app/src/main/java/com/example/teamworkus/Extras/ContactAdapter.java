package com.example.teamworkus.Extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.teamworkus.R;

import java.text.Format;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<ContactDetails> contactDetails = new ArrayList<>();
    private Context context;

    public ContactAdapter(ArrayList<ContactDetails> contactDetails, Context context) {
        this.contactDetails = contactDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        holder.textID.setText(String.valueOf(contactDetails.get(position).getId()));
        holder.textTitle.setText(contactDetails.get(position).getTitle());

        GlideUrl url = new GlideUrl(contactDetails.get(position).getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

        Glide.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return contactDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textID, textTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textID = itemView.findViewById(R.id.id);
            textTitle = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.loadImage);
        }
    }
}
