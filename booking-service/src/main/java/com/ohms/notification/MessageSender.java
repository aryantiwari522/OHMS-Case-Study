package com.ohms.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String guestEmail, String subject, String body){

		System.out.println("Sending message in queue...");
		MessageClass messageClass = new MessageClass(guestEmail, subject, body);
		
		rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,
                "message_routing_key", messageClass);
		
        System.out.println("Message sent successfully in queue...");
    }

}
