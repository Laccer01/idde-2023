package edu.bbte.idde.vlim2099.spring.controller.dto.incoming;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
public class UsedCarOwnerUpdateDto {
    @NotNull
    private String email;

    @NotNull
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<UsedCar> usedCars;
}
