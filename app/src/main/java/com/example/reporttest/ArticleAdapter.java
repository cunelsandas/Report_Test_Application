package com.example.reporttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    ArrayList<Article> articles;

    public ArticleAdapter() {
        articles = new ArrayList<>();
    }

    public void setData(ArrayList<Article> articles){
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View articleView = layoutInflater.inflate(R.layout.recycle_row,parent,false);


        return new ArticleViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        Article article = articles.get(position);
        Picasso.get().load(article.avatar_url).into(holder.avatar_url);
        holder.id.setText(article.id);
        holder.type.setText(article.type);
        holder.display_login.setText(article.display_login);
        holder.gravatar_id.setText(article.gravatar_id);
        holder.url.setText(article.url);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
