package com.atsistemas.demo.colas;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


@SpringBootApplication
@EnableRabbit
public class ConsumidorApplication implements RabbitListenerConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorApplication.class, args);
	}
	
	   final static String queueName = "miCola";
	   final static String exchangeName = "miExchange";
	   
	   	//Configuración de la cola
	    @Bean
	    Queue queue() {
	    	return new Queue(queueName, false);
	    }
	    
	    //Configuración del buzón
	    @Bean
	    TopicExchange exchange() {
	        return new TopicExchange(exchangeName);
	    }
	    
	    //Enlazador entre la cola y el buzón.
	    @Bean
	    Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(queueName);
	    }
	    
	    //Configuración del tipo de listener con un tipo de manejador de mensajes basado JSON
		@Override
		public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
			registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
		}
	    
		//Manejador de mensajes
		@Bean
		public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
			DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
			factory.setMessageConverter(consumerJackson2MessageConverter());
			return factory;
		}

		//Converter a JSON
		@Bean
		public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
			return new MappingJackson2MessageConverter();
		}
		

}
