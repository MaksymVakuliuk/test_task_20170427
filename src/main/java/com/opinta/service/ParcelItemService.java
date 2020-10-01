package com.opinta.service;

import com.opinta.dto.ParcelItemDto;
import com.opinta.entity.ParcelItem;

import java.util.List;

public interface ParcelItemService {

    List<ParcelItem> getAllEntities();

    List<ParcelItemDto> getAll();

    ParcelItem getEntityById(long id);

    ParcelItemDto getById(long id);

    ParcelItem saveEntity(ParcelItem parcelItem);

    ParcelItemDto save(ParcelItemDto parcelItemDto);

    ParcelItemDto update(long id, ParcelItemDto parcelItemDto);

    boolean delete(long id);
}
