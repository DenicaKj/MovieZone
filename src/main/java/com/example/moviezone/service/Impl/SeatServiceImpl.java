package com.example.moviezone.service.Impl;

import com.example.moviezone.model.*;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.repository.ProjectionIsPlayedInRoomRepository;
import com.example.moviezone.repository.Projection_RoomRepository;
import com.example.moviezone.repository.SeatRepository;
import com.example.moviezone.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {


    private final SeatRepository seatRepository;
    private final TicketServiceImpl ticketService;
    private final ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository;
    private final Projection_RoomRepository projection_roomRepository;

    public SeatServiceImpl(SeatRepository seatRepository, TicketServiceImpl ticketService, ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository, Projection_RoomRepository projection_roomRepository) {
        this.seatRepository = seatRepository;
        this.ticketService = ticketService;
        this.projectionIsPlayedInRoomRepository = projectionIsPlayedInRoomRepository;
        this.projection_roomRepository = projection_roomRepository;
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
    public List<Seat> findAllByRoomAndCategory(Projection projection, Projection_Room projectionRoom, Category category) {
        List<Ticket> tickets=ticketService.findAllTickets();
        List<Seat> seats=seatRepository.findAllByCategoryAndProjection(category,projectionRoom);

        for (int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getProjection()==projection){
                if(seats.contains(tickets.get(i).getSeat())){
                    seats.remove(tickets.get(i).getSeat());
                }
            }
        }
        return seats;
    }

    @Override
    public Optional<Seat> getSeatById(int id) {
        return Optional.of(seatRepository.getById(id));
    }
}
