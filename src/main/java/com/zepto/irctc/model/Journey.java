package com.zepto.irctc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name = "Journey")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String origin;
    @Column
    String destination;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Calendar startTime;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Calendar endTime;

    // Train is part of journey, many journies can be done through a single train
    // This will be the owning entity, means it will define the relationship
    // Default fetch type eager
    @ManyToOne
    @JoinColumn(name = "fk_train", nullable = false)
    Train train;
}
