package fr.ynov.shary.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ChangePasswordDTO {

    @Setter
    private Long id;

    @Setter
    private String oldPassword;

    @Setter
    private String newPassword;
}
