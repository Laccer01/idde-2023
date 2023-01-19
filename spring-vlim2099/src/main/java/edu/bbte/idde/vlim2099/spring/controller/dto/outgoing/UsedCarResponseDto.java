package edu.bbte.idde.vlim2099.spring.controller.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsedCarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private Double engineSize;
    private Integer horsePower;
    private Double numberOfKm;
    private Integer yearOfManufacture;
}
