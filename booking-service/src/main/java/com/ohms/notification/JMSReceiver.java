package com.ohms.notification;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Component
public class JMSReceiver implements ChannelAwareMessageListener{
	
	@Autowired
	private EmailNotification emailNotification;

	@Override
	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception{

		byte[] byteArray = message.getBody();
		MessageClass messageClass = (MessageClass) getObject(byteArray);
		
		System.out.println(messageClass.getBody());
		System.out.println(messageClass.getGuestEmail());
		System.out.println(messageClass.getSubject());
		
		emailNotification.sendEmail(messageClass.getGuestEmail(), messageClass.getSubject(), messageClass.getBody());
		
		System.out.println("Email Notification Called");
		/*
		 * Positively acknowledge a single delivery, the message will be discarded
		 */
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	/* De serialize the byte array to object */
	private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
		ObjectInput in = new ObjectInputStream(bis);
		return in.readObject();
	}
}
