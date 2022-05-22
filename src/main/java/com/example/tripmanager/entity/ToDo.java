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
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,
               CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", action='" + action + '\'' +
                '}';
    }


}
