package guard.agro.mapper;

import guard.agro.dto.request.PropertyPostRequest;
import guard.agro.dto.request.PropertyPutRequest;
import guard.agro.dto.request.UserPutRequest;
import guard.agro.dto.response.PropertyGetResponse;
import guard.agro.dto.response.PropertyPostResponse;
import guard.agro.dto.response.PropertyPutResponse;
import guard.agro.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertyMapper {

    PropertyGetResponse toPropertyGetResponse(Property property);

    List<PropertyGetResponse> toPropertyGetResponseList(List<Property> properties);

    Property toProperty(PropertyPostRequest request);

    PropertyPostResponse toPropertyPostResponse(Property property);

    Property toProperty (PropertyPutRequest request);

    PropertyPutResponse toPropertyPutResponse(Property property);

}
