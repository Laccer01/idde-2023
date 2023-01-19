package edu.bbte.idde.vlim2099.spring.controller.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsedCarOwnerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private java.sql.Date birthDay;
    private String gender;
    private String email;
    private String address;
}
