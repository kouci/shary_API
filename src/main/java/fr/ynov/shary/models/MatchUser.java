package fr.ynov.shary.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="matchusers")
@AllArgsConstructor
@NoArgsConstructor
public class MatchUser {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long match_id;



    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User userRequester;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "accepter_id", nullable = false)
    private User userAccepter;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
