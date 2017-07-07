package com.atsistemas.demo.colas;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductorRabbitMQ {
	
	@Autowired
    private RabbitTemplate rt;

   
    public void enviarMensaje(List<Venta> listaVentas) {
    	System.out.println("Enviando Mensaje");
        rt.convertAndSend(ProductorApplication.exchangeName, ProductorApplication.queueName,listaVentas);
        System.out.println("Mensaje Enviado");
    }

}
