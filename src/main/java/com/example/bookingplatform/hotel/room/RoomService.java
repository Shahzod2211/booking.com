package com.example.bookingplatform.hotel.room;

import com.example.bookingplatform.hotel.HotelRepository;
import com.example.bookingplatform.hotel.entity.Hotel;
import com.example.bookingplatform.hotel.room.dto.RoomCreateDTO;
import com.example.bookingplatform.hotel.room.entity.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomDTOMapper roomDTOMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Transactional
    public void create(RoomCreateDTO roomCreateDTO) {
        Room room = roomDTOMapper.toEntity(roomCreateDTO);
        Hotel hotel = hotelRepository.findById(roomCreateDTO.getHotelId()).orElseThrow();
        room.setHotel(hotel);
        roomRepository.save(room);
    }

    @Transactional
    public Integer delete(Integer id) {
        Integer hotelId = roomRepository.findById(id).orElseThrow().getHotel().getId();
        roomRepository.deleteById(id);
        return hotelId;
    }
}
