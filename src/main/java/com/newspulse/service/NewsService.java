package com.newspulse.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.newspulse.constants.AppConstants;
import com.newspulse.entity.News;
import com.newspulse.entity.Preferences;
import com.newspulse.model.NewsAPIModel;
import com.newspulse.model.NewsAPIResponseModel;
import com.newspulse.repository.NewsRepository;
import com.newspulse.repository.PreferenceRepository;

import jakarta.annotation.PostConstruct;

import static java.lang.Thread.sleep;

@Service
public class NewsService {

    @Autowired
    private NewsRepository _newsRepository;

    @Autowired
    private PreferenceRepository _preferences;

    @Autowired
    private PreferenceService _preferenceService;

    private static HttpURLConnection getHttpURLConnection(String category) throws IOException {
        StringBuilder apiUrl = new StringBuilder(AppConstants.BASE_URL);
        apiUrl.append("top-headlines?apikey=");
        apiUrl.append(AppConstants.API_KEY);
        apiUrl.append("&category=");
        apiUrl.append(category);
        apiUrl.append("&max=");
        apiUrl.append(AppConstants.DEFAULT_PAGE_SIZE);
        apiUrl.append("&lang=en");

        System.out.println("API URL: " + apiUrl);

        URL url = new URL(apiUrl.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    public void pullNews() {
        try {
            List<Preferences> allPreferences = _preferences.findAll();
            for (Preferences preference : allPreferences) {
                this.fetchNews(preference.getCategory());
                sleep(2000);
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

    public News fetchNews(String category) {
        try {
            System.out.println("Fetching news for category: " + category);
            HttpURLConnection connection = getHttpURLConnection(category);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Gson gson = new Gson();
                NewsAPIResponseModel newsAPIResponseModel = gson.fromJson(response.toString(), NewsAPIResponseModel.class);
                List<NewsAPIModel> newsList = newsAPIResponseModel.getArticles();
                newsList.forEach(news -> {
                    News newsEntity = new News();
                    newsEntity.setTitle(news.getTitle());
                    newsEntity.setDescription(news.getDescription());
                    newsEntity.setContent(news.getContent());
                    newsEntity.setLink(news.getUrl());
                    newsEntity.setPublishedAt(news.getPublishedAt());
                    newsEntity.setSource(news.getSource().getName());
                    _newsRepository.save(newsEntity);
                    _preferenceService.mapNewsToPreferences(newsEntity, category);
                });
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
                System.out.println("Response Message: " + connection.getResponseMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
