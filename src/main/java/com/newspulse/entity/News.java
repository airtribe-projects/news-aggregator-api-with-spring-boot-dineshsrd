package com.newspulse.entity;

import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;
    @Column(length = 10000)
    private String title;
    @Column(length = 10000)
    private String description;
    @Column(length = 10000)
    private String content;
    @Column(length = 10000)
    private String link;
    private String publishedAt;
    private String source;

    @ManyToMany(mappedBy = "_newsList")
    @JsonBackReference
    private List<Preferences> preferencesList;

}
