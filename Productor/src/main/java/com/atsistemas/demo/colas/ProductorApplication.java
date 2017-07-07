package com.atsistemas.demo.colas;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductorApplication.class, args);
	}
	
	   final static String queueName = "miCola";
	   final static String exchangeName = "miExchange";
	   
	    //Configuración de la cola
	    @Bean
	    public Queue queue() {
	        return new Queue(queueName, false);
	    }
	    
	   //Configuración del buzón
	    @Bean
	    public  TopicExchange exchange() {
	        return new TopicExchange(exchangeName);
	    }
	    
	    //Relacionamos la cola y el buzón
	    @Bean
	    public Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(queueName);
	    }
	    
	    //Con este objeto configuramos el objeto y el formato con el que envíamos los mensajes (en este caso JSON)
		@Bean
		public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
			final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
			return rabbitTemplate;
		}
		
		//Creación del convertidor a JSON
		@Bean
		public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
			return new Jackson2JsonMessageConverter();
		}

}
