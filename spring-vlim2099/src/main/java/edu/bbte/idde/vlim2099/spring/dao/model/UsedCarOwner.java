package edu.bbte.idde.vlim2099.spring.dao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

//Lombok a boiler kód generáláshoz
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor()
@Entity
@Table(name = "SpringUsedCarOwner")

public class UsedCarOwner extends BaseEntity {
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<UsedCar> usedCars;

    public void setOwner(UsedCarOwner newUsedCarOwner) {
        this.firstName = newUsedCarOwner.firstName;
        this.lastName = newUsedCarOwner.lastName;
        this.birthDay = newUsedCarOwner.birthDay;
        this.gender = newUsedCarOwner.gender;
        this.email = newUsedCarOwner.email;
        this.address = newUsedCarOwner.address;
        this.usedCars = newUsedCarOwner.usedCars;
    }
}
