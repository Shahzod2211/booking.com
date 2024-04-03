package com.example.bookingplatform.hotel.dto;

import com.example.bookingplatform.address.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelUpdateDto {
    private String name;
    private City city;
}
