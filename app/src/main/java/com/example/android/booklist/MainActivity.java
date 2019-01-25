package com.example.android.booklist;

import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputText = (EditText) findViewById(R.id.text_input);
                String searchInfo = inputText.getText().toString();
                String requestUrl = "https://www.googleapis.com/books/v1/volumes?q=" + searchInfo + "&maxResults=30";
                BookAsyncTask task = new BookAsyncTask();
                task.execute(requestUrl);
            }
        });

    }





    private void updateUI(ArrayList<Book> books) {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);

    }
    private class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            return QueryUtils.extractBooks(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            updateUI(books);
        }
    }
}
