package com.example.alexander.memoryleak;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Alexander on 03.07.2016.
 */
public class News {
	@NonNull
	private final String title;
	@NonNull
	private final Date date;

	public News(@NonNull String content, Date date) {
		this.title = content;
		this.date = date;
	}

	@NonNull
	public String getTitle() {
		return title;
	}

	@NonNull
	public Date getDate() {
		return date;
	}
}
