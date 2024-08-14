package com.newspulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newspulse.entity.NewsPulseUser;

@Repository
public interface UserRepository extends JpaRepository<NewsPulseUser, Integer> {
}
