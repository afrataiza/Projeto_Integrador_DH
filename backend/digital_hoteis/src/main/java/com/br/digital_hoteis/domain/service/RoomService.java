package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface RoomService {
    Room findRoomById(UUID id);
    Page<Room> findAllRooms(Pageable page);
    Room createRoom(Room host);
    Room updateRoom(UUID id, Map<String, Object> fields);
    void deleteRoomById(UUID id);
}
