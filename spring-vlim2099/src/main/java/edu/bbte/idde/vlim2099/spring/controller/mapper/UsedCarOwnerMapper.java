package edu.bbte.idde.vlim2099.spring.controller.mapper;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerUpdateDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarOwnerResponseDto;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.mapstruct.Mapper;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UsedCarOwnerMapper {

    UsedCarOwnerResponseDto modelToDto(UsedCarOwner usedCarOwner);

    Collection<UsedCarOwnerResponseDto> modelsToDtos(Collection<UsedCarOwner> usedCarOwner);

    UsedCarOwner creationDtoToModel(UsedCarOwnerCreationDto dto);

    UsedCarOwner updateDtoToModel(UsedCarOwnerUpdateDto dto);
}
