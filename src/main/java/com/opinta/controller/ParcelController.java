package com.opinta.controller;

import com.opinta.dto.ParcelDto;
import com.opinta.dto.ParcelDto;
import com.opinta.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/parcels")
public class ParcelController {
    public static final String NO_PARCEL_FOUND_FOR_ID = "No parcel found for ID %d";
    private final ParcelService parcelService;

    @Autowired
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
    
    @GetMapping
    @ResponseStatus(OK)
    public List<ParcelDto> getAllParcels() {
        return this.parcelService.getAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getParcel(@PathVariable("id") long id) {
        ParcelDto parcelDto = parcelService.getById(id);
        if (parcelDto == null) {
            return new ResponseEntity<>(format(NO_PARCEL_FOUND_FOR_ID, id), NOT_FOUND);
        }
        return new ResponseEntity<>(parcelDto, OK);
    }

    @PostMapping
    public ResponseEntity<?> createParcel(@RequestBody ParcelDto parcelDto) {
        parcelDto = parcelService.save(parcelDto);
        if (parcelDto == null) {
            return new ResponseEntity<>("New parcel has not been saved", BAD_REQUEST);
        }
        return new ResponseEntity<>(parcelDto, OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> updateParcel(@PathVariable long id, @RequestBody ParcelDto parcelDto) {
        parcelDto = parcelService.update(id, parcelDto);
        if (parcelDto != null) {
            return new ResponseEntity<>(parcelDto, OK);
        } else {
            return new ResponseEntity<>(format(NO_PARCEL_FOUND_FOR_ID, id), NOT_FOUND);
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteParcel(@PathVariable long id) {
        if (!parcelService.delete(id)) {
            return new ResponseEntity<>(format("No parcel item found for ID %d", id), NOT_FOUND);
        }
        return new ResponseEntity<>(OK);
    }
}
