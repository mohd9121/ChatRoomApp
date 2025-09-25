package com.chat.Controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chat.Loader.MessageRequest;
import com.chat.Model.Message;
import com.chat.Model.Room;
import com.chat.Repository.RoomRepository;
import com.chat.AppConstants;

@Controller
@CrossOrigin(AppConstants.FRONT_END)
public class ChatController {

    @Autowired
    private RoomRepository roomRepo;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, MessageRequest request) throws Exception {

        // ✅ Always use roomId from @DestinationVariable
        Optional<Room> roomOpt = roomRepo.findByRoomId(roomId);

        if (roomOpt.isEmpty()) {
            throw new RuntimeException("Room not Found!");
        }

        Room room = roomOpt.get();

        Message msg = new Message();
        msg.setSender(request.getSender());
        msg.setContent(request.getContent());
        msg.setTimestamp(LocalDateTime.now());

        room.getMessages().add(msg);
        roomRepo.save(room);

        return msg;
    }
}
