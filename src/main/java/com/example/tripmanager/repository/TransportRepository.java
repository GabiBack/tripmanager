package com.example.tripmanager.repository;

import com.example.tripmanager.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
}
