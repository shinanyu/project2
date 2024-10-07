package com.project2.hct.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project2.hct.dto.ChatRoomDTO;

import jakarta.annotation.PostConstruct;

@Service
public class ChatRoomService {

    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDTO findByRoomId(String roomId) {
    	
    	return chatRoomDTOMap.get(roomId);
    }
    public ChatRoomDTO createChatRoomDTO(String id,String name){
        ChatRoomDTO room = ChatRoomDTO.create(id, name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
}