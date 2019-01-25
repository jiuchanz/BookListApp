package com.example.android.booklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout bookLinearLayout;
        public BookViewHolder(LinearLayout ll) {
            super(ll);
            bookLinearLayout = ll;
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        BookViewHolder vh = new BookViewHolder(linearLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        LinearLayout ll = bookViewHolder.bookLinearLayout;
        TextView nameView = (TextView) ll.findViewById(R.id.name);
        nameView.setText(books.get(i).getName());
        TextView authorView = (TextView) ll.findViewById(R.id.author);
        authorView.setText(books.get(i).getAuthor());
        TextView isbnView = (TextView) ll.findViewById(R.id.isbn);
        isbnView.setText(books.get(i).getISBN());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
