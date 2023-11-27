package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.RoomApi;
import com.br.digital_hoteis.app.api.dto.request.CreateRoomRequest;
import com.br.digital_hoteis.app.api.dto.response.RoomDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.RoomSummaryResponse;
import com.br.digital_hoteis.domain.entity.Room;
import com.br.digital_hoteis.domain.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class RoomController implements RoomApi {

    private final RoomService roomService;
    @Override
    public ResponseEntity<Page<RoomSummaryResponse>> findAllRooms(Pageable page) {
        Page<Room> pageRoom = roomService.findAllRooms(page);
        Page<RoomSummaryResponse> response = pageRoom
                .map(room -> new RoomSummaryResponse(
                        room.getId(), room.getDescription(), room.getPrice()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RoomDetailedResponse> findRoomById(UUID roomId) {
        Room room = roomService.findRoomById(roomId);

        RoomDetailedResponse response = new RoomDetailedResponse(
                room.getId(),
                room.getDescription(),
                room.getMax_number_of_guests(),
                room.isHas_private_bathroom(),
                room.isHas_bathtub(),
                room.isHas_kitchen(),
                room.isHas_stove(),
                room.isHas_microwave(),
                room.isAre_pets_allowed(),
                room.getPrice()
        );

        return null;
    }

    @Override
    public ResponseEntity<Void> createRoom(CreateRoomRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateRoom(UUID roomId, Map<String, Object> fields) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRoomById(UUID roomId) {
        return null;
    }
}
