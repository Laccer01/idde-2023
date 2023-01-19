package edu.bbte.idde.vlim2099.spring.controller;

import edu.bbte.idde.vlim2099.spring.controller.dto.exception.NotFoundException;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarOwnerResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarMapper;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarOwnerMapper;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
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

@Slf4j
@RestController
@RequestMapping("/usedCarsOwner")
public class UsedCarOwnersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarController.class);
    @Autowired
    private UsedCarOwnerDao usedCarOwnerDao;
    @Autowired
    private UsedCarDao usedCarDao;

    @Autowired
    private UsedCarMapper usedCarMapper;
    @Autowired
    private UsedCarOwnerMapper usedCarOwnerMapper;

    // ha megvan adva paraméternek a brand név akkor meghívodik a szűrés, ellenkező
    // esetben listázodik az összes autó
    @GetMapping
    @ResponseBody
    public Collection<UsedCarOwnerResponseDto> findAll(
            @RequestParam(value = "lastName", required = false) String lastName) {
        if (lastName != null) {
            LOGGER.info("All used car owners with lastname: {} have been found!", lastName);
            return usedCarOwnerMapper.modelsToDtos(usedCarOwnerDao.findByLastName(lastName));
        }
        LOGGER.info("All used car owners have been found!");
        return usedCarOwnerMapper.modelsToDtos(usedCarOwnerDao.findAll());
    }

    @GetMapping("/{id}")
    public UsedCarOwnerResponseDto findById(@PathVariable("id") Long id) {
        LOGGER.info("The used car owners with the id: {} have been found!", id);
        return usedCarOwnerMapper.modelToDto(usedCarOwnerDao.getById(id));
    }

    @GetMapping("/{id}/usedCars")
    public Collection<UsedCarResponseDto> findUsedCarOwnersById(@PathVariable("id") Long id) {
        UsedCarOwner result = usedCarOwnerDao.getById(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return usedCarMapper.modelsToDtos(result.getUsedCars());
    }

    @PostMapping
    public UsedCarOwnerResponseDto  create(@RequestBody @Valid UsedCarOwnerCreationDto dto) {
        LOGGER.info("The new used car owner have been created successfully!");
        UsedCarOwner newUsedCarOwner = usedCarOwnerMapper.creationDtoToModel(dto);
        usedCarOwnerDao.saveAndFlush(newUsedCarOwner);
        return usedCarOwnerMapper.modelToDto(newUsedCarOwner);

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

        usedCarDao.saveAndFlush(newUsedCar);
        usedCarOwnerDao.saveAndFlush(usedCarOwner);

        return usedCarMapper.modelToDto(newUsedCar);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UsedCarOwnerCreationDto dto) {
        LOGGER.info("The used owner car with the id: {} have been updated successfully!", id);
        UsedCarOwner updateCarOwner = usedCarOwnerMapper.creationDtoToModel(dto);
        updateCarOwner.setId(id);
        usedCarOwnerDao.saveAndFlush(updateCarOwner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UsedCarOwner usedCarOwner) {
        LOGGER.info("The used car owner with the id: {} have been deleted successfully!", usedCarOwner);
        usedCarOwnerDao.delete(usedCarOwner);
    }

    @DeleteMapping("/{id}/usedCars/{usedCarId}")
    public void deleteUsedCar(@PathVariable("id") Long id, @PathVariable("usedCarId") Long usedCarId) {
        UsedCarOwner usedCarOwner = usedCarOwnerDao.getById(id);
        UsedCar usedCar = usedCarDao.getById(usedCarId);

        Collection<UsedCar> usedCars = usedCarOwner.getUsedCars();
        usedCars.remove(usedCar);
        usedCarOwner.setUsedCars(usedCars);
        usedCarOwnerDao.saveAndFlush(usedCarOwner);

        usedCar.setUsedCarOwner(null);
        usedCarDao.saveAndFlush(usedCar);
        usedCarDao.delete(usedCar);
    }

}
