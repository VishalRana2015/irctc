package com.zepto.irctc.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class SeatID implements Serializable {
    Integer seatNumber;
    @ManyToOne // Default fetch type is eager
    // This is the owning entity
    Coache coache;
}
