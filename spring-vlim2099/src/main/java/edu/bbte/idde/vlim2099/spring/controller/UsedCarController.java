package edu.bbte.idde.vlim2099.spring.controller;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarMapper;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarController.class);

    //ha megvan adva paraméternek a brand név akkor meghívodik a szűrés, ellenkező esetben listázodik az összes autó
    @GetMapping
    @ResponseBody
    public Collection<UsedCarResponseDto> findAll(@RequestParam(required = false) String brand) {
        if (brand == null) {
            LOGGER.info("All used cars have been found!");
            return UsedCarMapper.INSTANCE.modelsToDtos(usedCarDao.findAll());
        } else {
            LOGGER.info("All used cars with brand: {} have been found!", brand);
            return UsedCarMapper.INSTANCE.modelsToDtos(usedCarDao.findByBrand(brand));
        }

    }

    @GetMapping("/{id}")
    public UsedCarResponseDto findById(@PathVariable("id") Long id) {
        LOGGER.info("The used car with the id: {} have been found!", id);
        return UsedCarMapper.INSTANCE.modelToDto(usedCarDao.findById(id));
    }

    @PostMapping
    public void create(@RequestBody @Valid UsedCarCreationDto dto) {
        LOGGER.info("The new used car have been created successfully!");
        usedCarDao.create(UsedCarMapper.INSTANCE.creationDtoToModel(dto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UsedCarCreationDto dto) {
        LOGGER.info("The used car with the id: {} have been updated successfully!",id);
        UsedCar updateCar = UsedCarMapper.INSTANCE.creationDtoToModel(dto);
        usedCarDao.update(updateCar,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("The used car with the id: {} have been deleted successfully!",id);
        usedCarDao.delete(id);
    }

}
