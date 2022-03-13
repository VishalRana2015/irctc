package com.zepto.irctc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketNo;

// A user can book many tickets
    @ManyToOne //This is the owning entity, write now, we are not saving any references in the User Entity for Tickets, but we can
    User user ;

    // A journey has many tickets
    @ManyToOne // Many Tickets can be of a same journey, and this is the owning entity for this relationship.
            // But a Ticket can't belong to more than one more journey.
    Journey journey;

    // Many Seats can be booked in a Ticket, but a seat can only be booked at most in one Ticket.

    @OneToMany // This is the owning entity, means it defines the relationship.
    List<Seat> seats;

}
