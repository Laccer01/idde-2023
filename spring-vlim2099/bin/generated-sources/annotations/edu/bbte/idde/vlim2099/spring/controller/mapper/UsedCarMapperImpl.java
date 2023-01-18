package edu.bbte.idde.vlim2099.spring.controller.mapper;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarUpdateDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T21:07:41+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20221012-0724, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class UsedCarMapperImpl implements UsedCarMapper {

    @Override
    public UsedCarResponseDto modelToDto(UsedCar usedCar) {
        if ( usedCar == null ) {
            return null;
        }

        UsedCarResponseDto usedCarResponseDto = new UsedCarResponseDto();

        usedCarResponseDto.setBrand( usedCar.getBrand() );
        usedCarResponseDto.setEngineSize( usedCar.getEngineSize() );
        usedCarResponseDto.setHorsePower( usedCar.getHorsePower() );
        usedCarResponseDto.setModel( usedCar.getModel() );
        usedCarResponseDto.setNumberOfKm( usedCar.getNumberOfKm() );
        usedCarResponseDto.setUsedCarOwner( usedCar.getUsedCarOwner() );
        usedCarResponseDto.setYearOfManufacture( usedCar.getYearOfManufacture() );

        return usedCarResponseDto;
    }

    @Override
    public Collection<UsedCarResponseDto> modelsToDtos(Collection<UsedCar> usedCars) {
        if ( usedCars == null ) {
            return null;
        }

        Collection<UsedCarResponseDto> collection = new ArrayList<UsedCarResponseDto>( usedCars.size() );
        for ( UsedCar usedCar : usedCars ) {
            collection.add( modelToDto( usedCar ) );
        }

        return collection;
    }

    @Override
    public UsedCar creationDtoToModel(UsedCarCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsedCar usedCar = new UsedCar();

        usedCar.setBrand( dto.getBrand() );
        usedCar.setChassisNumber( dto.getChassisNumber() );
        usedCar.setEngineSize( dto.getEngineSize() );
        usedCar.setHorsePower( dto.getHorsePower() );
        usedCar.setModel( dto.getModel() );
        usedCar.setNumberOfKm( dto.getNumberOfKm() );
        usedCar.setPrice( dto.getPrice() );
        usedCar.setYearOfManufacture( dto.getYearOfManufacture() );

        return usedCar;
    }

    @Override
    public UsedCar updateDtoToModel(UsedCarUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsedCar usedCar = new UsedCar();

        usedCar.setEngineSize( dto.getEngineSize() );
        usedCar.setHorsePower( dto.getHorsePower() );
        usedCar.setNumberOfKm( dto.getNumberOfKm() );
        usedCar.setPrice( dto.getPrice() );
        usedCar.setUsedCarOwner( dto.getUsedCarOwner() );

        return usedCar;
    }
}
