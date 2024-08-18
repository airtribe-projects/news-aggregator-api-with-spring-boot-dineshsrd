package com.newspulse.entity;

import java.util.List;

import org.springframework.boot.actuate.endpoint.web.Link;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long preferenceId;

    private String category;

    @ManyToMany
    @JoinTable(
            name = "userPreference",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<NewsPulseUser> _usersList;

    @ManyToMany
    @JoinTable(
            name = "newsPreference",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id")
    )
    private List<News> _newsList;
}
