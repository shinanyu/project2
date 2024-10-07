package com.project2.hct.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class ChatRoomDTO {
	
	private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoomDTO create(String roomId, String roomName){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = roomId;
        room.roomName = roomName;
        return room;
    }
}
