package edu.bbte.idde.vlim2099.spring.controller;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarMapper;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/usedCars")
public class UsedCarController {
    @Autowired
    private UsedCarDao usedCarDao;

    @Autowired
    private UsedCarOwnerDao usedCarOwnerDao;

    @Autowired
    private UsedCarMapper usedCarMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarController.class);

    //ha megvan adva paraméternek a brand név akkor meghívodik a szűrés, ellenkező esetben listázodik az összes autó
    @GetMapping
    @ResponseBody
    public Collection<UsedCarResponseDto> findAll(
            @RequestParam(value = "brand", required = false) String brand) {
        if (brand != null) {
            LOGGER.info("All used cars with brand: {} have been found!", brand);
            return usedCarMapper.modelsToDtos(usedCarDao.findByBrand(brand));
        }

        LOGGER.info("All used cars have been found!");
        return usedCarMapper.modelsToDtos(usedCarDao.findAll());
    }

    @GetMapping("/{id}")
    public UsedCarResponseDto findById(@PathVariable("id") Long id) {
        LOGGER.info("The used car with the id: {} have been found!", id);
        return usedCarMapper.modelToDto(usedCarDao.getById(id));
    }

    @PostMapping
    public UsedCarResponseDto create(@RequestBody @Valid UsedCarCreationDto dto) {
        LOGGER.info("The new used car have been created successfully!");
        UsedCar newUsedCar = usedCarMapper.creationDtoToModel(dto);

        UsedCarOwner usedCarOwner = usedCarOwnerDao.getById(Long.valueOf(dto.getUsedCarOwnerId()));
        Collection<UsedCar> usedCarsFromOwner = usedCarOwner.getUsedCars();
        if (usedCarsFromOwner != null) {
            usedCarsFromOwner.add(newUsedCar);

            usedCarOwner.setUsedCars(usedCarsFromOwner);

            newUsedCar.setUsedCarOwner(usedCarOwner);
        }

        usedCarDao.saveAndFlush(newUsedCar);
        return usedCarMapper.modelToDto(newUsedCar);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UsedCarCreationDto dto) {
        LOGGER.info("The used car with the id: {} have been updated successfully!", id);
        UsedCar updateCar = usedCarMapper.creationDtoToModel(dto);
        updateCar.setId(id);
        usedCarDao.saveAndFlush(updateCar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long usedCarId) {
        LOGGER.info("The used car with the id: {} have been deleted successfully!", usedCarId);
        UsedCar usedCar = usedCarDao.getById(usedCarId);
        usedCarDao.delete(usedCar);
    }

}
