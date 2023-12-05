package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Room;
import com.br.digital_hoteis.domain.exception.RoomNotFoundException;
import com.br.digital_hoteis.domain.repository.RoomRepository;
import com.br.digital_hoteis.domain.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;
@Slf4j
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ObjectMapper mapper;
    @Override
    public Room findRoomById(UUID id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public Page<Room> findAllRooms(Pageable page) {
        return roomRepository.findAll(page);
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(UUID id, Map<String, Object> fields) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
        Room input = mapper.convertValue(fields, Room.class);
        fields.forEach((property, value) -> {
            Field field = findField(Room.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, room, newValue);
        });
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
        roomRepository.delete(room);
    }
}
