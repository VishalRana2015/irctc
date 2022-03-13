package com.zepto.irctc.model;

import enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    String username;
    @Column
    String password; // we are not saving passwords in encrypted way right now,
    @Column
    @Enumerated(EnumType.STRING)
    Role role;
}
