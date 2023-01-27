package edu.bbte.idde.vlim2099.spring.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "SpringSearchUsedCar")

public class SearchUsedCar extends BaseEntity {
    @NotNull
    private java.time.Instant date;
    private String brandName;

}
