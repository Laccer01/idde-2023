package edu.bbte.idde.vlim2099.spring.controller.dto.incoming;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UsedCarUpdateDto {

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
    private Integer price;
}
