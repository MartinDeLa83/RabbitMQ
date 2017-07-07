package com.atsistemas.demo.colas;

import java.util.Date;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

@Service
public class ListenerRabbitMQ {
	
    
	//Nuestro listener apuntará a la cola en la que estamos enviando los mensajes
    @RabbitListener(queues = ConsumidorApplication.queueName)
    public void receiveMessage(Message mensaje)  {
    	//Recuperamos el cuerpo del mensaje
		String json = new String(mensaje.getBody());		
		
		//Creación de adpatador para los tipos Date que en el JSON vienen como un Long
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonAdapter, typeOfT, context) -> new Date(jsonAdapter.getAsJsonPrimitive().getAsLong()))
		        .create();
				
		//Transformación del JSON a un array de objetos Venta
		Venta[] arrayVentas = gson.fromJson(json, Venta[].class);
		
		//Tratamiento de los objetos obtenidos
		for (Venta v : arrayVentas) {
			//Pintamos por consola
			System.out.println(v.toString());
     }

    }


}
