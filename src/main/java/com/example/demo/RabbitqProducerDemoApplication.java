package com.example.demo;

import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class RabbitqProducerDemoApplication {
	
	private final RabbitTemplate template;

	public static void main(String[] args) {
		
		SpringApplication.run(RabbitqProducerDemoApplication.class, args);
	}
	
	@Autowired
	public RabbitqProducerDemoApplication(RabbitTemplate template)
	{
		this.template = template;
	}
	
	 @Scheduled(fixedRate = 1000)
	    public void sendMessage() {
	        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
	        String message = "Hello Muleeeeee world! " + timestamp;

	        this.template.convertAndSend("spring-boot", message);
	    }

	    @Bean
	    public Queue queue() {
	        return new Queue("spring-boot-mule", false);
	    }
	    
	    @Scheduled(fixedRate = 10000)
	    public void sendMessage1() {
	        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
	        String message = "Now The Muleeeeee Satrted! " + timestamp;

	        this.template.convertAndSend("spring-boot-mule", message);
	        
	    }

	    @Bean
	    public Queue queue1() {
	        return new Queue("spring-boot-mule", false);
	    }

	    
}
