package com.example.tripmanager.repository;

import com.example.tripmanager.entity.UserTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public
interface UserTripRepository extends JpaRepository<UserTrip, Long> {
}


