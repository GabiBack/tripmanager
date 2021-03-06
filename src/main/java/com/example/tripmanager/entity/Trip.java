package com.example.tripmanager.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="place", nullable = false)
    private String place;

    @Column(name="date_start", nullable = false)
    private String dateStart;

    @Column(name="date_end", nullable = false)
    private String dateEnd;

    @Column(name="days")
    private int days;

}
