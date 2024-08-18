package com.newspulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newspulse.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    News findByTitle(String title);
}
