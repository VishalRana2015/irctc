package com.zepto.irctc.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Seat")
@Data
public class Seat {
    @EmbeddedId
    SeatID seatId;

    @Column
    boolean isAvailable = false;


}
