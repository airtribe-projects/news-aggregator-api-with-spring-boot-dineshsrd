package com.newspulse.repository;

import com.newspulse.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preferences, Integer> {
  Preferences findByCategory(String category);
}
