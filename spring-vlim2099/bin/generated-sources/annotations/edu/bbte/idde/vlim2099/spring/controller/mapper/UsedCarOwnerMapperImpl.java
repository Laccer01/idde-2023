package edu.bbte.idde.vlim2099.spring.controller.mapper;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerUpdateDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarOwnerResponseDto;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T21:17:31+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20221012-0724, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class UsedCarOwnerMapperImpl implements UsedCarOwnerMapper {

    @Override
    public UsedCarOwnerResponseDto modelToDto(UsedCarOwner usedCarOwner) {
        if ( usedCarOwner == null ) {
            return null;
        }

        UsedCarOwnerResponseDto usedCarOwnerResponseDto = new UsedCarOwnerResponseDto();

        usedCarOwnerResponseDto.setAddress( usedCarOwner.getAddress() );
        usedCarOwnerResponseDto.setBirthDay( usedCarOwner.getBirthDay() );
        usedCarOwnerResponseDto.setEmail( usedCarOwner.getEmail() );
        usedCarOwnerResponseDto.setFirstName( usedCarOwner.getFirstName() );
        usedCarOwnerResponseDto.setGender( usedCarOwner.getGender() );
        usedCarOwnerResponseDto.setId( usedCarOwner.getId() );
        usedCarOwnerResponseDto.setLastName( usedCarOwner.getLastName() );

        return usedCarOwnerResponseDto;
    }

    @Override
    public Collection<UsedCarOwnerResponseDto> modelsToDtos(Collection<UsedCarOwner> usedCarOwner) {
        if ( usedCarOwner == null ) {
            return null;
        }

        Collection<UsedCarOwnerResponseDto> collection = new ArrayList<UsedCarOwnerResponseDto>( usedCarOwner.size() );
        for ( UsedCarOwner usedCarOwner1 : usedCarOwner ) {
            collection.add( modelToDto( usedCarOwner1 ) );
        }

        return collection;
    }

    @Override
    public UsedCarOwner creationDtoToModel(UsedCarOwnerCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsedCarOwner usedCarOwner = new UsedCarOwner();

        usedCarOwner.setAddress( dto.getAddress() );
        usedCarOwner.setBirthDay( dto.getBirthDay() );
        usedCarOwner.setEmail( dto.getEmail() );
        usedCarOwner.setFirstName( dto.getFirstName() );
        usedCarOwner.setGender( dto.getGender() );
        usedCarOwner.setLastName( dto.getLastName() );

        return usedCarOwner;
    }

    @Override
    public UsedCarOwner updateDtoToModel(UsedCarOwnerUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsedCarOwner usedCarOwner = new UsedCarOwner();

        usedCarOwner.setAddress( dto.getAddress() );
        usedCarOwner.setEmail( dto.getEmail() );
        Collection<UsedCar> collection = dto.getUsedCars();
        if ( collection != null ) {
            usedCarOwner.setUsedCars( new ArrayList<UsedCar>( collection ) );
        }

        return usedCarOwner;
    }
}
