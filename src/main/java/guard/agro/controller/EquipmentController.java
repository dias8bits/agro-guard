package guard.agro.controller;

import guard.agro.dto.request.EquipmentPostRequest;
import guard.agro.dto.request.EquipmentPutRequest;
import guard.agro.dto.response.EquipmentGetResponse;
import guard.agro.dto.response.EquipmentPostResponse;
import guard.agro.dto.response.EquipmentPutResponse;
import guard.agro.mapper.EquipmentMapper;
import guard.agro.service.EquipmentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("v1/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;

    private final EquipmentMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List equipments")
    public ResponseEntity<List<EquipmentGetResponse>> findAllOrById(@RequestParam(required = false) Long id) {
        log.debug("request to list all equipments, param id: '{}'", id);

        var equipments = equipmentService.findAll(id);

        var equipmentGetResponseList = mapper.toEquipmentGetResponseList(equipments);

        return ResponseEntity.ok(equipmentGetResponseList);
    }

    @GetMapping("property/{propertyId}")
    @ApiResponse(responseCode = "200", description = "Find Equipments by Property Id")
    public ResponseEntity<List<EquipmentGetResponse>> findAllByPropertyId(@PathVariable Long propertyId) {
        log.debug("request to list equipments of property with id: '{}'", propertyId);

        var equipments = equipmentService.findAllByPropertyId(propertyId);

        var equipmentGetResponseList = mapper.toEquipmentGetResponseList(equipments);

        return ResponseEntity.ok(equipmentGetResponseList);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Equipment created")
    public ResponseEntity<EquipmentPostResponse> save(@Valid @RequestBody EquipmentPostRequest request) {
        log.debug("request to save equipment");

        var equipmentToSave = mapper.toEquipment(request);

        var equipmentSaved = equipmentService.save(equipmentToSave);

        var equipmentPostResponse = mapper.toEquipmentPostResponse(equipmentSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentPostResponse);
    }

    @PutMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Equipment updated")
    public ResponseEntity<EquipmentPutResponse> update(@Valid @RequestBody EquipmentPutRequest request,
                                                       @PathVariable Long id) {
        log.debug("request to update equipment with id: '{}'", id);

        var equipmentToUpdate = mapper.toEquipment(request);

        var equipmentUpdated = equipmentService.update(equipmentToUpdate, id);

        var equipmentPutResponse = mapper.toEquipmentPutResponse(equipmentUpdated);

        return ResponseEntity.ok(equipmentPutResponse);
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "204", description = "Equipment deleted")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("request to delete equipment with id: '{}'", id);

        var equipmentToDelete = equipmentService.findEquipmentByIdOrElseThrow(id);

        equipmentService.delete(equipmentToDelete);

        return ResponseEntity.noContent().build();
    }

}
