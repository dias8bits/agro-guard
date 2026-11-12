package guard.agro.controller;

import guard.agro.dto.request.OccurrencePostRequest;
import guard.agro.dto.request.OccurrencePutRequest;
import guard.agro.dto.response.OccurrenceGetResponse;
import guard.agro.dto.response.OccurrencePostResponse;
import guard.agro.dto.response.OccurrencePutResponse;
import guard.agro.mapper.OccurrenceMapper;
import guard.agro.service.OccurrenceService;
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
@RequestMapping("v1/occurrences")
public class OccurrenceController {

    private final OccurrenceService occurrenceService;

    private final OccurrenceMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List Occurrences")
    public ResponseEntity<List<OccurrenceGetResponse>> findAllOrById(@RequestParam(required = false) Long id) {
        log.debug("request to list all occurrences, param id: '{}'", id);

        var occurrences = occurrenceService.findAll(id);

        var occurrenceGetResponseList = mapper.toOccurrenceGetResponseList(occurrences);

        return ResponseEntity.ok(occurrenceGetResponseList);
    }

    @GetMapping("property/{propertyId}")
    @ApiResponse(responseCode = "200", description = "Find Occurrence by Property Id")
    public ResponseEntity<List<OccurrenceGetResponse>> findAllByPropertyId(@PathVariable Long propertyId) {
        log.debug("request to list occurrences of property with id: '{}'", propertyId);

        var occurrences = occurrenceService.findAllByPropertyId(propertyId);

        var occurrenceGetResponseList = mapper.toOccurrenceGetResponseList(occurrences);

        return ResponseEntity.ok(occurrenceGetResponseList);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Occurrence created")
    public ResponseEntity<OccurrencePostResponse> save(@Valid @RequestBody OccurrencePostRequest request) {
        log.debug("request to save occurrence");

        var occurrenceToSave = mapper.toOccurrence(request);

        var occurrenceSaved = occurrenceService.save(occurrenceToSave);

        var occurrencePostResponse = mapper.toOccurrencePostResponse(occurrenceSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(occurrencePostResponse);
    }

    @PutMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Occurrence updated")
    public ResponseEntity<OccurrencePutResponse> update(@Valid @RequestBody OccurrencePutRequest request,
                                                        @PathVariable Long id) {
        log.debug("request to update occurrence with id: '{}'", id);

        var occurrenceToUpdate = mapper.toOccurrence(request);

        var occurrenceUpdated = occurrenceService.update(occurrenceToUpdate, id);

        var occurrencePutResponse = mapper.toOccurrencePutResponse(occurrenceUpdated);

        return ResponseEntity.ok(occurrencePutResponse);
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "204", description = "Occurrence deleted")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("request to delete occurrence with id: '{}'", id);

        var occurrenceToDelete = occurrenceService.findOccurrenceByIdOrElseThrow(id);

        occurrenceService.delete(occurrenceToDelete);

        return ResponseEntity.noContent().build();
    }

}
