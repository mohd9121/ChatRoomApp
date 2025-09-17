package com.chat.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.Model.Room;

public interface RoomRepository extends MongoRepository<Room, String> 
{
	Optional<Room> findByRoomId(String roomId);
}
