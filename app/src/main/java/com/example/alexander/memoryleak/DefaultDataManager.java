package com.example.alexander.memoryleak;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander on 03.07.2016.
 */
public class DefaultDataManager implements DataManager {
    @Override
    public List<String> fetchNews(NewsType newsType) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("Росссия победила на евро 2016", "Сборную России допустили до Олимпиады в Рио", "Мутко получил Нобелевскую премию");
    }
}
