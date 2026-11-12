package guard.agro.mapper;

import guard.agro.dto.request.PropertyPostRequest;
import guard.agro.dto.request.PropertyPutRequest;
import guard.agro.dto.response.PropertyGetResponse;
import guard.agro.dto.response.PropertyPostResponse;
import guard.agro.dto.response.PropertyPutResponse;
import guard.agro.model.Property;
import guard.agro.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "employees", target = "employeeIds")
    PropertyGetResponse toPropertyGetResponse(Property property);

    List<PropertyGetResponse> toPropertyGetResponseList(List<Property> properties);

    @Mapping(source = "ownerId", target = "owner.id")
    Property toProperty(PropertyPostRequest request);

    @Mapping(source = "owner.id", target = "ownerId")
    PropertyPostResponse toPropertyPostResponse(Property property);

    @Mapping(source = "ownerId", target = "owner.id")
    Property toProperty(PropertyPutRequest request);

    @Mapping(source = "owner.id", target = "ownerId")
    PropertyPutResponse toPropertyPutResponse(Property property);

    default Set<Long> mapEmployeeIds(Set<User> employees) {
        if (employees == null) {
            return new HashSet<>();
        }

        return employees.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

}