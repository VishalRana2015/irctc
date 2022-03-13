package com.zepto.irctc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "Train")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;

    // A train can have many coaches, from 0 to many
    // The train itself is the owning side of the relationship
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    List<Coache> coaches ;
    // You can use JoinTable( joinColumns) to customize the names of the table that will be created.
    // Default fetch type is LAZY

    public void setCoaches(ArrayList<Coache> coaches) {
        this.coaches = coaches;
    }
    // we need to check edge cases, for the given below sentences.
    public boolean addCoachToTrain( Coache coache){
        if ( this.coaches == null)
            this.coaches = new ArrayList<>();
        if ( !this.coaches.contains(coache)) {
            this.coaches.add(coache);
            coache.setIsAvailable(false);
            return true;
        }
        return false;
    }

    public boolean removeCoache( Coache coache){
        if ( this.coaches == null )
            return false;
        // Since there is only one EntityManager, therefore once the object is retrieved from the database,
        // all the different variables would refer to the same objet at the end.
        if ( coaches.contains(coache)){
            coaches.remove(coache);
            coache.setIsAvailable(true);
            return true;
        }
        return false;
    }

}
