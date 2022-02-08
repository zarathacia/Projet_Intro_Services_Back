package com.example.services.repo;

import com.example.services.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
