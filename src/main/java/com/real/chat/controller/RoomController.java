package com.real.chat.controller;

import com.real.chat.model.Message;
import com.real.chat.model.Room;
import com.real.chat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:5173") // allow frontend
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // ✅ Create Room (accepts plain text)
    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        // Clean up quotes if frontend sends text with quotes
        roomId = roomId.replace("\"", "").trim();

        // Validate input
        if (roomId.isEmpty()) {
            return ResponseEntity.badRequest().body("Room ID cannot be empty!");
        }

        // Check if room already exists
        if (roomRepository.findByRoomId(roomId) != null) {
            return ResponseEntity.badRequest().body("Room already exists!");
        }

        // Create and save new room
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    // ✅ Join Room API
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found!");
        }
        return ResponseEntity.ok(room);
    }

    // ✅ Get Messages of Room API
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size) {

        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found!");
        }

        List<Message> messages = room.getMessages();
        if (messages == null || messages.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        // Simple pagination
        int totalMessages = messages.size();
        int start = Math.max(0, totalMessages - (page + 1) * size);
        int end = Math.min(totalMessages, start + size);
        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessages);
    }
}
