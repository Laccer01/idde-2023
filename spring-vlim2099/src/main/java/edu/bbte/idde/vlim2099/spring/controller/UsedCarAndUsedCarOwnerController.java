package edu.bbte.idde.vlim2099.spring.controller;

import edu.bbte.idde.vlim2099.spring.controller.dto.exception.NotFoundException;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarMapper;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/usedCarAndUsedCarOwner")
public class UsedCarAndUsedCarOwnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarController.class);
    @Autowired
    private UsedCarOwnerDao usedCarOwnerDao;
    @Autowired
    private UsedCarMapper usedCarMapper;

    @GetMapping("/{id}/usedCars")
    public Collection<UsedCarResponseDto> findUsedCarOwnersById(@PathVariable("id") Long id) {
        UsedCarOwner result = usedCarOwnerDao.getById(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return usedCarMapper.modelsToDtos(result.getUsedCars());
    }

    @PostMapping("/{id}/usedCars")
    public UsedCarResponseDto createUsedCar(@RequestBody @Valid UsedCarCreationDto dto,
                                            @PathVariable("id") Long id) {
        LOGGER.info("The new used car for owner with id: {} have been created successfully!", id);

        UsedCar newUsedCar = usedCarMapper.creationDtoToModel(dto);

        UsedCarOwner usedCarOwner = usedCarOwnerDao.getById(id);
        Collection<UsedCar> usedCarsFromOwner = usedCarOwner.getUsedCars();
        usedCarsFromOwner.add(newUsedCar);
        usedCarOwner.setUsedCars(usedCarsFromOwner);

        newUsedCar.setUsedCarOwner(usedCarOwner);

        usedCarOwnerDao.saveAndFlush(usedCarOwner);

        return usedCarMapper.modelToDto(newUsedCar);
    }

    @DeleteMapping("/{id}/usedCars/{usedCarId}")
    public void deleteUsedCar(@PathVariable("id") Long id, @PathVariable("usedCarId") Long usedCarId) {
        UsedCarOwner usedCarOwner = usedCarOwnerDao.getById(id);

        Collection<UsedCar> usedCars = usedCarOwner.getUsedCars();
        UsedCar usedCar = null;
        for (UsedCar iterator : usedCars) {
            if (Objects.equals(iterator.getId(), usedCarId)) {
                usedCar = iterator;
            }
        }

        usedCars.remove(usedCar);
        usedCarOwner.setUsedCars(usedCars);
        if (usedCar != null) {
            usedCar.setUsedCarOwner(null);
            ///nem tudtad hogy töröljem véglegesen az autok közűl ha nincs dao hozzá ezért állítottam át az id-t nullra

        }

        usedCarOwnerDao.saveAndFlush(usedCarOwner);

    }
}
