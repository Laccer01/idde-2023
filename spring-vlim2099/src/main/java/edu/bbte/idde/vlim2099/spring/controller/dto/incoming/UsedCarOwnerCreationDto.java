package edu.bbte.idde.vlim2099.spring.controller.dto.incoming;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;

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
