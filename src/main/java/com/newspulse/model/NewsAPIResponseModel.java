package com.newspulse.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsAPIResponseModel {
    private int totalArticles;
    private List<NewsAPIModel> articles;
}
