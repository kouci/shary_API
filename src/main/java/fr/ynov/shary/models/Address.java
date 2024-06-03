package fr.ynov.shary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long address_id;


    @Getter
    @Setter
    @Column(name = "street")
    private String street;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "postal_code")
    private Integer postal_code;

}