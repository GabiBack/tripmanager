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
@Table(name = "transports")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="transport_name", nullable = false)
    private String name;

    @Column(name="transport_start", nullable = false)
    private String startDate;

    @Column(name="transport_start_place", nullable = false)
    private String startPlace;

    @Column(name="transport_end", nullable = false)
    private String endDate;

    @Column(name="transport_end_place", nullable = false)
    private String endPlace;

    @Column(name="transport_order", nullable = false)
    private int order;

    @Column(name="notes")
    private String notes;

    @NonNull
    @ManyToOne
    @JoinColumn(name="tripId", nullable = false)
    private Trip trip;


}
