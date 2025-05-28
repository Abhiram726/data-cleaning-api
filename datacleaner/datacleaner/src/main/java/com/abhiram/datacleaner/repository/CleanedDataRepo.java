package com.abhiram.datacleaner.repository;

import com.abhiram.datacleaner.model.CleanedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleanedDataRepo extends JpaRepository<CleanedData, Long> {
}
