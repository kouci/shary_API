package fr.ynov.shary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name= "userLog")
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long userLog_id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name= "event_description")
    private String eventDescription;

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

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}
