package fr.ynov.shary.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class MatchUserDTO {

    @Getter
    @Setter
    private long requesterId;

    @Getter
    @Setter
    private long accepterId;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    public MatchUserDTO() {
    }

    public MatchUserDTO(long requesterId, long accepterId, Date createdAt, Date updatedAt) {
        this.requesterId = requesterId;
        this.accepterId = accepterId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}