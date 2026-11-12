package guard.agro.mapper;

import guard.agro.dto.request.OccurrencePostRequest;
import guard.agro.dto.request.OccurrencePutRequest;
import guard.agro.dto.response.OccurrenceGetResponse;
import guard.agro.dto.response.OccurrencePostResponse;
import guard.agro.dto.response.OccurrencePutResponse;
import guard.agro.model.Occurrence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OccurrenceMapper {

    @Mapping(source = "propertyId", target = "property.id")
    @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
    Occurrence toOccurrence(OccurrencePostRequest request);

    @Mapping(source = "propertyId", target = "property.id")
    @Mapping(target = "dateTime", ignore = true)
    Occurrence toOccurrence(OccurrencePutRequest request);

    @Mapping(source = "property.id", target = "propertyId")
    OccurrenceGetResponse toOccurrenceGetResponse(Occurrence occurrence);

    List<OccurrenceGetResponse> toOccurrenceGetResponseList(List<Occurrence> occurrences);

    @Mapping(source = "property.id", target = "propertyId")
    OccurrencePostResponse toOccurrencePostResponse(Occurrence occurrence);

    @Mapping(source = "property.id", target = "propertyId")
    OccurrencePutResponse toOccurrencePutResponse(Occurrence occurrence);

}