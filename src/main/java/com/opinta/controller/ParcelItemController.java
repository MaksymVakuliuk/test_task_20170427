package com.opinta.controller;

import com.opinta.dto.ParcelItemDto;
import com.opinta.service.ParcelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/parcel-items")
public class ParcelItemController {
    public static final String NO_PARCEL_ITEM_FOUND_FOR_ID = "No parcel item found for ID %d";
    private final ParcelItemService parcelItemService;
    
    @Autowired
    public ParcelItemController(ParcelItemService parcelItemService) {
        this.parcelItemService = parcelItemService;
    }
    
    @GetMapping
    @ResponseStatus(OK)
    public List<ParcelItemDto> getAllParcelItems() {
        return this.parcelItemService.getAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") long id) {
        ParcelItemDto parcelItemDto = parcelItemService.getById(id);
        if (parcelItemDto == null) {
            return new ResponseEntity<>(format(NO_PARCEL_ITEM_FOUND_FOR_ID, id), NOT_FOUND);
        }
        return new ResponseEntity<>(parcelItemDto, OK);
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ParcelItemDto parcelItemDto) {
        parcelItemDto = parcelItemService.save(parcelItemDto);
        if (parcelItemDto == null) {
            return new ResponseEntity<>("New parcel item has not been saved", BAD_REQUEST);
        }
        return new ResponseEntity<>(parcelItemDto, OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@PathVariable long id, @RequestBody ParcelItemDto parcelItemDto) {
        parcelItemDto = parcelItemService.update(id, parcelItemDto);
        if (parcelItemDto != null) {
            return new ResponseEntity<>(parcelItemDto, OK);
        } else {
            return new ResponseEntity<>(format(NO_PARCEL_ITEM_FOUND_FOR_ID, id), NOT_FOUND);
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id) {
        if (!parcelItemService.delete(id)) {
            return new ResponseEntity<>(format("No  parcel item  found for ID %d", id), NOT_FOUND);
        }
        return new ResponseEntity<>(OK);
    }
}
