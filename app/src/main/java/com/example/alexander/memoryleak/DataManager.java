package com.example.alexander.memoryleak;

import java.util.List;

/**
 * Created by Alexander on 03.07.2016.
 */
public interface DataManager {
    List<String> fetchNews(NewsType newsType);
}
