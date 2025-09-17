package com.chat.Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message 
{
private String content;
private String sender;
private LocalDateTime timestamp;
public Message(String content, String sender) 
{
	
	this.content = content;
	this.sender = sender;
	this.timestamp=LocalDateTime.now();
}



}
