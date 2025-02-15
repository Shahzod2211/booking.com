package com.example.bookingplatform.hotel;

import com.example.bookingplatform.hotel.dto.HotelCreateDTO;
import com.example.bookingplatform.hotel.dto.HotelResponseDTO;
import com.example.bookingplatform.hotel.dto.HotelUpdateDto;
import com.example.bookingplatform.hotel.room.entity.Room;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public String createHotel(@ModelAttribute HotelCreateDTO hotelCreateDto) throws IOException {
        hotelService.create(hotelCreateDto);
        return "index";
    }

    @GetMapping
    public String getAllHotels(Model model) {
        List<HotelResponseDTO> responseDtos = hotelService.getAllHotels();
        model.addAttribute("hotels", responseDtos);
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "hotel/create";
    }

    @GetMapping("/{id}/rooms")
    public String getHotelRooms(@PathVariable Integer id,
                                @RequestParam Integer roomCount,
                                @RequestParam LocalDateTime startDate,
                                @RequestParam LocalDateTime endDate,
                                Model model) {
        List<Room> rooms = hotelService.getRooms(id, roomCount, startDate, endDate);
        model.addAttribute("rooms", rooms);
        return "rooms/rooms";
    }

    @GetMapping("/{id}/update")
    public String getUpdateHotel(@PathVariable Integer id, Model model) {
        HotelResponseDTO hotelResponseDto = hotelService.getById(id);
        model.addAttribute("hotel", hotelResponseDto);
        return "hotel/update";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Integer id, @ModelAttribute HotelUpdateDto updateDto) {
        hotelService.update(id, updateDto);
        return "index";
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<Resource> downloadPicture(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return hotelService.downloadFile(id, response);
    }
}
