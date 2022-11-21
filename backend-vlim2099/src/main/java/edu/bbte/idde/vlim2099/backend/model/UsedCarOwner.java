package edu.bbte.idde.vlim2099.backend.model;

import lombok.*;

//Lombok a boiler kód generáláshoz
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor()

public class UsedCarOwner extends BaseEntity  {
    private String firstName;
    private String lastName;
    private java.sql.Date birthDay;
    private String gender;
    private String email;
    private String address;
    private Integer usedCarId;

    public void setOwner(UsedCarOwner newUsedCarOwner) {
        this.firstName = newUsedCarOwner.firstName;
        this.lastName = newUsedCarOwner.lastName;
        this.birthDay = newUsedCarOwner.birthDay;
        this.gender = newUsedCarOwner.gender;
        this.email = newUsedCarOwner.email;
        this.address = newUsedCarOwner.address;
        this.usedCarId = newUsedCarOwner.usedCarId;
    }
}
