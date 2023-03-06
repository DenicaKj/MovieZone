package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Category;
import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;
import com.example.moviezone.model.Ticket;
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
    public List<Seat> findAllByRoomAndCategory(Projection_Room projectionRoom, Category category) {
        List<Ticket> tickets=ticketService.findAllTickets();
        List<Seat> seats=seatRepository.findAllByCategoryAndProjection(category,projectionRoom);
        List<Seat> s=new ArrayList<>();
        List<Projection_Room> projection_rooms=new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<ProjectionIsPlayedInRoom> projectionIsPlayedInRooms= projectionIsPlayedInRoomRepository.findAllByProjectionId(tickets.get(i).getProjection().getId_projection());
            for (int j = 0; j < projectionIsPlayedInRooms.size(); j++) {
                projection_rooms.add(projection_roomRepository.getById(projectionIsPlayedInRooms.get(j).getIdroom()));
            }
        }
        int f=0;
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < tickets.size(); j++) {
                if(seats.get(i).getId_seat().equals(tickets.get(j).getSeat().getId_seat())){
                    for (int k = 0; k < projection_rooms.size(); k++) {
                        if(seats.get(i).getProjection().getId_room().equals(projection_rooms.get(k).getId_room()))
                            f=1;
                    }
                }

            }

            if(f==0){
                s.add(seats.get(i));
            }
            f=0;
        }
        return s;
    }

    @Override
    public Optional<Seat> getSeatById(int id) {
        return Optional.of(seatRepository.getById(id));
    }
}
