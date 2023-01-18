package edu.bbte.idde.vlim2099.spring.controller.dto.outgoing;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsedCarResponseDto {
    private String brand;
    private String model;
    private Double engineSize;
    private Integer horsePower;
    private Double numberOfKm;
    private Integer yearOfManufacture;
}
