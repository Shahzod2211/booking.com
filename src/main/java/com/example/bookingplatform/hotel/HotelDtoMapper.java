package com.example.bookingplatform.hotel;

import com.example.bookingplatform.hotel.dto.HotelCreateDTO;
import com.example.bookingplatform.hotel.dto.HotelResponseDTO;
import com.example.bookingplatform.hotel.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HotelDtoMapper {
    private final ModelMapper modelMapper;

    public HotelResponseDTO toResponseDto(Hotel hotel) {
        return modelMapper.map(hotel, HotelResponseDTO.class);
    }

    public List<HotelResponseDTO> toResponseDtos(List<Hotel> hotels) {
        return hotels
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public Hotel toEntity(HotelCreateDTO hotelCreateDto) {
        return modelMapper.map(hotelCreateDto, Hotel.class);
    }
}
