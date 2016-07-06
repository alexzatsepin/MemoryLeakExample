package com.example.alexander.memoryleak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ListView newsList;
    private ArrayAdapter<News> newsAdapter;
    private Bitmap picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsList = (ListView)findViewById(R.id.newsList);
        newsAdapter = new NewsAdapter(this, R.layout.news_item);
        newsList.setAdapter(newsAdapter);
        loadPicture();
        final Button button = (Button) findViewById(R.id.showNewsButton);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AsyncTask<NewsType, Void, List<News>>() {

                        @Override
                        protected List<News> doInBackground(NewsType... params) {
                            DataManager dataManager = new DefaultDataManager();
                            List<String> rawNews = dataManager.fetchNews(params[0]);
                            return new NewsMapper().toNews(rawNews);
                        }

                        @Override
                        protected void onPostExecute(List<News> news) {
                            updateNewsList(news);
                        }
                    }.execute(NewsType.SPORT);
                }
            });
        }
    }

    private void loadPicture() {
        try {
            InputStream bitmap= getAssets().open("icon.png");
            picture = BitmapFactory.decodeStream(bitmap);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void updateNewsList(@NonNull List<News> news) {
        newsAdapter.clear();
        newsAdapter.addAll(news);
    }

    private static class NewsAdapter extends ArrayAdapter<News> {
        public NewsAdapter(Context context, @LayoutRes int resourceId) {
            super(context, resourceId);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            News news = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            }
            TextView title = (TextView) convertView.findViewById(R.id.newsTitle);
            TextView date = (TextView) convertView.findViewById(R.id.newsDate);

            title.setText(news.getTitle());
            date.setText(news.getDate().toString());
            return convertView;
        }
    }
}
