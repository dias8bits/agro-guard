package guard.agro.controller;

import guard.agro.dto.request.PropertyPostRequest;
import guard.agro.dto.request.PropertyPutRequest;
import guard.agro.dto.response.PropertyGetResponse;
import guard.agro.dto.response.PropertyPostResponse;
import guard.agro.dto.response.PropertyPutResponse;
import guard.agro.mapper.PropertyMapper;
import guard.agro.service.PropertyService;
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
@RequestMapping("v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    private final PropertyMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List properties")
    public ResponseEntity<List<PropertyGetResponse>> findAllOrById(@RequestParam(required = false) Long id) {
        log.debug("request to list all properties, param id: '{}'", id);

        var properties = propertyService.findAll(id);

        var propertyGetResponseList = mapper.toPropertyGetResponseList(properties);

        return ResponseEntity.ok(propertyGetResponseList);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Property created")
    public ResponseEntity<PropertyPostResponse> save(@Valid @RequestBody PropertyPostRequest request) {
        log.debug("request to save property");

        var propertyToSave = mapper.toProperty(request);

        var propertySaved = propertyService.save(propertyToSave);

        var propertyPostResponse = mapper.toPropertyPostResponse(propertySaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(propertyPostResponse);
    }

    @PutMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Property updated")
    public ResponseEntity<PropertyPutResponse> update(@Valid @RequestBody PropertyPutRequest request,
                                                      @PathVariable Long id) {
        log.debug("request to update property with id: '{}'", id);

        var propertyToUpdate = mapper.toProperty(request);

        var propertyUpdated = propertyService.update(propertyToUpdate, id);

        var propertyPutResponse = mapper.toPropertyPutResponse(propertyUpdated);

        return ResponseEntity.ok(propertyPutResponse);
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Property deleted")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("request to delete property with id: '{}'", id);

        var propertyToDelete = propertyService.findPropertyByIdOrElseThrow(id);

        propertyService.delete(propertyToDelete);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("{propertyId}/employees/{userId}")
    @ApiResponse(responseCode = "200", description = "Employee added to property")
    public ResponseEntity<PropertyGetResponse> addEmployee(@PathVariable Long propertyId,
                                                           @PathVariable Long userId) {
        log.debug("request to add user '{}' as employee of property '{}'", userId, propertyId);

        var property = propertyService.addEmployee(propertyId, userId);

        var propertyGetResponse = mapper.toPropertyGetResponse(property);

        return ResponseEntity.ok(propertyGetResponse);
    }

    @DeleteMapping("{propertyId}/employees/{userId}")
    @ApiResponse(responseCode = "204", description = "Employee removed from property")
    public ResponseEntity<Void> removeEmployee(@PathVariable Long propertyId,
                                               @PathVariable Long userId) {
        log.debug("request to remove user '{}' from employees of property '{}'", userId, propertyId);

        propertyService.removeEmployee(propertyId, userId);

        return ResponseEntity.noContent().build();
    }

}
