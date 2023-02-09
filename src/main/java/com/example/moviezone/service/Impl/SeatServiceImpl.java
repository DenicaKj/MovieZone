package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Category;
import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;
import com.example.moviezone.repository.SeatRepository;
import com.example.moviezone.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {


    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> findAllByProjection_Room(Projection_Room projection_room) {
        return seatRepository.findAllByProjection(projection_room);
    }

    @Override
    public List<Seat> findAllByRoomAndCategory(Projection_Room projectionRoom, Category category) {
        return seatRepository.findAllByCategoryAndProjection(category,projectionRoom);
    }

    @Override
    public Optional<Seat> getSeatById(int id) {
        return Optional.of(seatRepository.getById(id));
    }
}
