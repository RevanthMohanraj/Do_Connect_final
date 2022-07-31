package com.wipro.DoConnectMicroChat.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.DoConnectMicroChat.Dto.MessageDto;
import com.wipro.DoConnectMicroChat.Entity.Message;
import com.wipro.DoConnectMicroChat.Repository.MessageRepository;



@Service
public class MessageService{
	@Autowired
	MessageRepository repo;

	public MessageDto sendMessage(MessageDto messageDTO) {

		Message message = new Message();
		System.out.println(messageDTO.getMessage());
		System.out.println(messageDTO.getMessage());

		message.setFromUser(messageDTO.getFromUser());
		message.setMessage(messageDTO.getMessage());
		message = repo.save(message);

		messageDTO.setFromUser(message.getFromUser());
		messageDTO.setMessage(message.getMessage());

		return messageDTO;
	}

	
	public List<MessageDto> getMessage() {
		List<MessageDto> data = new ArrayList<MessageDto>();
		List<Message> messages = repo.findAll();
		for (Message message : messages) {
			MessageDto messageDTO = new MessageDto();
			messageDTO.setFromUser(message.getFromUser());
			messageDTO.setMessage(message.getMessage());
			data.add(messageDTO);
		}
		return data;
	}

}