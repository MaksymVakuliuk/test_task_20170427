package com.opinta.mapper;

import com.opinta.dto.ParcelDto;
import com.opinta.entity.Parcel;
import com.opinta.entity.ParcelItem;
import com.opinta.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = ParcelItemMapper.class)
public interface ParcelMapper extends BaseMapper<ParcelDto, Parcel> {
    @Override
    @Mappings({
            @Mapping(target = "parcelItemIds",
                    expression = "java(getParcelItemIds(parcel))"),
            @Mapping(target = "shipmentId", expression = "java(parcel.getShipment().getId())")})
    ParcelDto toDto(Parcel parcel);

    @Override
    @Mappings(
            {@Mapping(target = "parcel.parcelItems",
                    expression = "java(createParcelItems(parcelDto))"),
            @Mapping(target = "parcel.shipment",
                    expression = "java(createShipment(parcelDto))")})
    Parcel toEntity(ParcelDto parcelDto);

    default long[] getParcelItemIds(Parcel parcel) {
        return parcel.getParcelItems().stream()
                .mapToLong(ParcelItem::getId)
                .toArray();
    }

    default Shipment createShipment(ParcelDto parcelDto) {
        Shipment shipment = new Shipment();
        shipment.setId(parcelDto.getShipmentId());
        return shipment;
    }

    default List<ParcelItem> createParcelItems(ParcelDto parcelDto) {
        List<ParcelItem> parcelItems = new ArrayList<>();
        for (long id : parcelDto.getParcelItemIds()) {
            parcelItems.add(createParcelItemById(id));
        }
        return parcelItems;
    }

    default ParcelItem createParcelItemById(long id) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setId(id);
        return parcelItem;
    }
}
