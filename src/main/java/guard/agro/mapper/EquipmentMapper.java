package guard.agro.mapper;

import guard.agro.dto.request.EquipmentPostRequest;
import guard.agro.dto.request.EquipmentPutRequest;
import guard.agro.dto.response.EquipmentGetResponse;
import guard.agro.dto.response.EquipmentPostResponse;
import guard.agro.dto.response.EquipmentPutResponse;
import guard.agro.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(source = "propertyId", target = "property.id")
    Equipment toEquipment(EquipmentPostRequest request);

    @Mapping(source = "propertyId", target = "property.id")
    Equipment toEquipment(EquipmentPutRequest request);

    @Mapping(source = "property.id", target = "propertyId")
    EquipmentGetResponse toEquipmentGetResponse(Equipment equipment);

    List<EquipmentGetResponse> toEquipmentGetResponseList(List<Equipment> equipments);

    @Mapping(source = "property.id", target = "propertyId")
    EquipmentPostResponse toEquipmentPostResponse(Equipment equipment);

    @Mapping(source = "property.id", target = "propertyId")
    EquipmentPutResponse toEquipmentPutResponse(Equipment equipment);

}