package edu.bbte.idde.vlim2099.spring.controller.mapper;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarUpdateDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UsedCarMapper {

    UsedCarResponseDto modelToDto(UsedCar usedCar);

    Collection<UsedCarResponseDto> modelsToDtos(Collection<UsedCar> usedCars);

    UsedCar creationDtoToModel(UsedCarCreationDto dto);

    UsedCar updateDtoToModel(UsedCarUpdateDto dto);

}
