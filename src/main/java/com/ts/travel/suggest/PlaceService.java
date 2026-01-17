package com.ts.travel.suggest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(@NonNull Long id) {
        return placeRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    public void deletePlace(@NonNull Long id) {
        placeRepository.deleteById(id);
    }
}
