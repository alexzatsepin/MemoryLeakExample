package com.example.alexander.memoryleak;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 03.07.2016.
 */
public class NewsMapper {
	@NonNull
	List<News> toNews(@NonNull List<String> strings) {
		List<News> newsList = new ArrayList<>();
		for (String newsTitle : strings) {
			News news = new News(newsTitle, new Date());
			newsList.add(news);
		}
		return newsList;
	}
}
