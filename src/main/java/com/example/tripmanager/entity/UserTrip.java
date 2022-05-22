package com.example.tripmanager.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_trip")
public class UserTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="trip_id", nullable = false)
    private Long tripId;

    @Column(name="user_id", nullable = false)
    private Long userId;
}
