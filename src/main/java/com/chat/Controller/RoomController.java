package com.chat.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chat.AppConstants;
import com.chat.Model.Message;
import com.chat.Model.Room;
import com.chat.Repository.RoomRepository;

@RequestMapping("/api/v1/rooms")
@RestController
@CrossOrigin(AppConstants.FRONT_END)
public class RoomController {
    @Autowired
    private RoomRepository roomRepo;

    // Create Room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody Map<String, String> body) {
        String roomId = body.get("roomId");
        if (roomId == null || roomId.isEmpty()) {
            return ResponseEntity.badRequest().body("Room ID cannot be empty!");
        }

        if (roomRepo.findByRoomId(roomId).isPresent()) {
            return ResponseEntity.badRequest().body("Room Already Exists!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepo.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    // Join Room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Optional<Room> room = roomRepo.findByRoomId(roomId);
        if (room.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found!");
        }
        return ResponseEntity.ok(room.get());
    }

    // Get Messages with pagination
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessage( @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size) {

        Optional<Room> roomOpt = roomRepo.findByRoomId(roomId);
        if (roomOpt.isEmpty()) 
        {
            return ResponseEntity.badRequest().build();
        }

        List<Message> msgs = roomOpt.get().getMessages();
        int start = Math.max(0, msgs.size() - (page + 1) * size);
        int end = Math.min(msgs.size(), start + size);

        List<Message> messages = msgs.subList(start, end);
        return ResponseEntity.ok(messages);
    }
}
