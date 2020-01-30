package com.example.reporttest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    ImageView avatar_url;
    TextView id;
    TextView type; //login
    TextView display_login;
    TextView gravatar_id;
    TextView url;


    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar_url = itemView.findViewById(R.id.avatar_url);
        id = itemView.findViewById(R.id.tv_id);
        type = itemView.findViewById(R.id.tv_type);
        display_login = itemView.findViewById(R.id.tv_displaylogin);
        gravatar_id = itemView.findViewById(R.id.tv_gravatarid);
        url = itemView.findViewById(R.id.tv_url);
    }
}
