package com.newspulse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsAPIModel {
    private String title;
    private String description;
    private String content;
    private String url;
    private String publishedAt;
    private Source source;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Source {
        private String name;
        private String url;
    }
}




