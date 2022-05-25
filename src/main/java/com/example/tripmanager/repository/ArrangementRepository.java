package com.example.tripmanager.repository;

import com.example.tripmanager.entity.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement, Long> {
}
