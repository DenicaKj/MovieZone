package com.example.moviezone.model.procedures;

import com.example.moviezone.model.Ticket;

public class TicketsCancelClass {
    public Ticket ticket;
    public boolean canCancel;

    public TicketsCancelClass(Ticket ticket, boolean canCancel) {
        this.ticket = ticket;
        this.canCancel = canCancel;
    }

    public TicketsCancelClass() {
    }
}
