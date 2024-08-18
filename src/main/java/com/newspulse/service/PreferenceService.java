package com.newspulse.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newspulse.entity.News;
import com.newspulse.entity.NewsPulseUser;
import com.newspulse.entity.Preferences;
import com.newspulse.repository.PreferenceRepository;

import jakarta.annotation.PostConstruct;

@Service
public class PreferenceService {

    private final String[] categories = {"business", "entertainment", "general", "health", "science", "sports", "technology"};

    @Autowired
    private PreferenceRepository _preferenceRepository;

    @PostConstruct
    public void loadPreferences() {
        if (_preferenceRepository.count() > 0) {
            System.out.println("Preferences already loaded");
        } else {
            for (String category : categories) {
                Preferences newsPreference = new Preferences();
                newsPreference.setCategory(category);
                _preferenceRepository.save(newsPreference);
            }
        }
    }

    public void mapUsersToPreferences(Object data, List<String> preferences) {
        NewsPulseUser user = (NewsPulseUser) data;
        System.out.println("Mapping user to preferences");
        for (String preference : preferences) {
            Preferences userPref = _preferenceRepository.findByCategory(preference);
            userPref.get_usersList().add(user);
            _preferenceRepository.save(userPref);
        }
    }

    public void mapNewsToPreferences(Object data, String preference) {
        News news = (News) data;
        System.out.println("Mapping news to preferences");
        Preferences newsPreference = _preferenceRepository.findByCategory(preference);
        newsPreference.get_newsList().add(news);
        _preferenceRepository.save(newsPreference);

    }
}
