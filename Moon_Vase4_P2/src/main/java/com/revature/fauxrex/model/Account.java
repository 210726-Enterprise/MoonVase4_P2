package com.revature.fauxrex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="account")

@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="usd", nullable = false)
    private Double usd;

    @Column(name="gbp", nullable = false)
    private Double gbp;

    @Column(name="eur", nullable = false)
    private Double eur;

    @Column(name="nzd", nullable = false)
    private Double nzd;

}
