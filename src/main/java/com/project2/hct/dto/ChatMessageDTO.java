package com.project2.hct.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChatMessageDTO {
    private String roomId;
    private String writer;
    private String message;
}