package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;
import com.example.moviezone.repository.SeatRepository;
import com.example.moviezone.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return seatRepository.findAllByProjection_room(projection_room);
    }
}
