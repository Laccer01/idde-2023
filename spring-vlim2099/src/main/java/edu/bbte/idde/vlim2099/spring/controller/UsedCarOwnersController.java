package edu.bbte.idde.vlim2099.spring.controller;

import edu.bbte.idde.vlim2099.spring.controller.dto.incoming.UsedCarOwnerCreationDto;
import edu.bbte.idde.vlim2099.spring.controller.dto.outgoing.UsedCarOwnerResponseDto;
import edu.bbte.idde.vlim2099.spring.controller.mapper.UsedCarOwnerMapper;
import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
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
    private UsedCarOwnerMapper usedCarOwnerMapper;

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

    @PostMapping
    public UsedCarOwnerResponseDto  create(@RequestBody @Valid UsedCarOwnerCreationDto dto) {
        LOGGER.info("The new used car owner have been created successfully!");
        UsedCarOwner newUsedCarOwner = usedCarOwnerMapper.creationDtoToModel(dto);
        usedCarOwnerDao.saveAndFlush(newUsedCarOwner);
        return usedCarOwnerMapper.modelToDto(newUsedCarOwner);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UsedCarOwnerCreationDto dto) {
        LOGGER.info("The used owner car with the id: {} have been updated successfully!", id);
        UsedCarOwner updateCarOwner = usedCarOwnerMapper.creationDtoToModel(dto);
        updateCarOwner.setId(id);
        usedCarOwnerDao.saveAndFlush(updateCarOwner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long usedCarOwnerId) {
        LOGGER.info("The used car owner with the id: {} have been deleted successfully!", usedCarOwnerId);
        UsedCarOwner usedCarOwner = usedCarOwnerDao.getById(usedCarOwnerId);
        usedCarOwnerDao.delete(usedCarOwner);

    }

}
