package com.sutapa.neworiyanewspaper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsItemClass> mNewsItemList;

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView mNewsPaperImageView;
        ImageView mFavIconImageView;
        TextView mEpaperTextView;
        TextView mWebsiteTextView;
        Context context;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            mNewsPaperImageView = itemView.findViewById(R.id.paperImage);
            mFavIconImageView = itemView.findViewById(R.id.icon);
            mEpaperTextView = itemView.findViewById(R.id.ePaper);
            mWebsiteTextView = itemView.findViewById(R.id.website);

        }
    }

    public NewsAdapter(ArrayList<NewsItemClass> newsList) {
        mNewsItemList = newsList;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {

        final NewsItemClass currentItem = mNewsItemList.get(position);
        holder.mNewsPaperImageView.setImageResource(currentItem.getImageResourceId());

        final String ePaperUrl = currentItem.getePaerpUrl();
        final String webSiteUrl = currentItem.getWebsiteUrl();

        if (TextUtils.isEmpty(ePaperUrl)) {
            //   Log.d("TAG","ePaper - "+url+" Website - "+currentItem.getWebsiteUrl());
            holder.mEpaperTextView.setVisibility(View.GONE);

        } else if (TextUtils.isEmpty(webSiteUrl)) {
            holder.mWebsiteTextView.setVisibility(View.GONE);

        }

        holder.mNewsPaperImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(holder.context,"ଆପଣ କଣ ୱେବସାଇଟ ରେ ପଢିବେ ନା ଆଜିର ପେପର ପଢିବେ ତା ଉପରେ କ୍ଲିକ କରନ୍ତୁ",Toast.LENGTH_LONG).show();

            }
        });

        holder.mEpaperTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //  Intent intent = new Intent(holder.context, NewsWebView.class);
                Intent intent = new Intent(holder.context, NewsAdvancedWebView.class);
                intent.putExtra("url", ePaperUrl);
                //  Log.d("TAG","URL is :"+url);
                holder.context.startActivity(intent);

            }
        });

        holder.mWebsiteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   String url = currentItem.getWebsiteUrl();

                Intent intent = new Intent(holder.context, NewsAdvancedWebView.class);
                intent.putExtra("url", webSiteUrl);
                Log.d("TAG", "URL is :" + webSiteUrl);
                holder.context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsItemList.size();
    }

}
