package edu.bbte.idde.vlim2099.spring.controller.dto.incoming;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UsedCarCreationDto {
    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    @Positive
    private Double engineSize;

    @NotNull
    @Positive
    private Integer horsePower;

    @NotNull
    @Positive
    private Double numberOfKm;

    @NotNull
    @Positive
    private Integer yearOfManufacture;

    @NotNull
    private String chassisNumber;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    private Integer usedCarOwnerId;

}
