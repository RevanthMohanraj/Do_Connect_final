package com.wipro.DoConnectMicroChat.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.DoConnectMicroChat.Dto.MessageDto;
import com.wipro.DoConnectMicroChat.Service.MessageService;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/sendMessage")
	public MessageDto sendMessage(@RequestBody MessageDto messageDTO) {
		System.out.println(messageDTO+"hii");
		return messageService.sendMessage(messageDTO);
	}

	@GetMapping("/getMessage")
	public List<MessageDto> getMessage() {
		return messageService.getMessage();
	}

}
