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
@Table(name = "arrangements")
public class Arrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "note")
    private String note;

    @Column(name = "type")
    private int type;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;


}
