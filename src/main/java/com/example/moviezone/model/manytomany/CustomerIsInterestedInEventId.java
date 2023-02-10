package com.example.moviezone.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerIsInterestedInEventId implements Serializable {
    Integer idcustomer;
    Integer idevent;

}
