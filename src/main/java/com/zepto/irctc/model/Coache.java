package com.zepto.irctc.model;

import enums.CoacheType;
import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Coache")
@Data
public class Coache {
    private static Integer AC_NON_AC_MAX_SEATS = 60;
    private static Integer SEATER_MAX_SEATS = 120;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    @Enumerated(EnumType.STRING)
    CoacheType type;
    //@Column( columnDefinition = "default false")
    @Column
    Boolean isAvailable;

    // A coach can be associated with a train.
    // we are making thi statement not insertable because, at the time of creation
    // we want this coache to be available for any train. Therefore excluding it from SQL insert statement.
    // By default it is nullable.
    @ManyToOne
    private Train train;


    // A coache can have many seats
    // AC - 60
    // NONAC - 60
    // Seater - 120
    // we will make the seats whenever a new coache will be made.
    @OneToMany(mappedBy = "seatId.coache")
    List<Seat> seats ;

    // You can't create coache without its type
    public Coache(){

    }
    public Coache(CoacheType type){
        this.type = type;
        this.isAvailable = true; // available to add in train
        seats = new ArrayList<>();
        if ( type == CoacheType.AC || type == CoacheType.NON_AC){
            for ( int i =1; i <= AC_NON_AC_MAX_SEATS; i++){
                SeatID seatID  = new SeatID();
                seatID.setCoache(this);
                seatID.setSeatNumber(i);
                Seat seat = new Seat();
                seat.setSeatId(seatID);
                seats.add(seat);
            }
        }
        else if ( type == CoacheType.SEATER ){
            for ( int i =1; i <= SEATER_MAX_SEATS ; i++) {
                SeatID seatID = new SeatID();
                seatID.setCoache(this);
                seatID.setSeatNumber(i);
                Seat seat = new Seat();
                seat.setSeatId(seatID);
                seats.add(seat);
            }
        }
    }
}
