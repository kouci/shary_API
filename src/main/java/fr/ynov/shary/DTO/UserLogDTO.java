package fr.ynov.shary.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserLogDTO {

    @Getter
    @Setter
    private String eventType;

    @Getter
    @Setter
    private String eventDescription;

    //TODO set format to datetime
    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    @Getter
    @Setter
    private long userId;

    public UserLogDTO() {
    }

    public UserLogDTO( String eventType, String eventDescription, Date createdAt, Date updatedAt, long userId) {
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }
}
