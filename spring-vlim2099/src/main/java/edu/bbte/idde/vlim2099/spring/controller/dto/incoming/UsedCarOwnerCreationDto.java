package edu.bbte.idde.vlim2099.spring.controller.dto.incoming;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UsedCarOwnerCreationDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private java.sql.Date birthDay;

    @NotNull
    private String gender;

    @NotNull
    private String email;

    @NotNull
    private String address;

}
