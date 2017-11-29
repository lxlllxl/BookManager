package com.example.administrator.bookmanager.admin.qiantai_admin;

/**
 * Created by Administrator on 2017/8/20.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.bookmanager.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private List<book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.book_image);
            textView = (TextView) view.findViewById(R.id.book_name);

        }

    }

    public BookAdapter(List<book> bookList) {
        mBookList = bookList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();

        }
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        book book1 = mBookList.get(position);
        holder.textView.setText(book1.getName());

        Glide.with(context).load(book1.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}
