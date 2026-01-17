package com.ts.travel.suggest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable @NonNull Long id) {
        return placeService.getPlaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Place createPlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable @NonNull Long id,
                                             @RequestBody Place placeDetails) {
        return placeService.getPlaceById(id)
                .map(existingPlace -> {
                    existingPlace.setCity(placeDetails.getCity());
                    existingPlace.setName(placeDetails.getName());
                    existingPlace.setCategory(placeDetails.getCategory());
                    existingPlace.setDescription(placeDetails.getDescription());
                    Place updatedPlace = placeService.savePlace(existingPlace);
                    return ResponseEntity.ok(updatedPlace);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable @NonNull Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}
