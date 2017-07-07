package com.atsistemas.demo.colas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class LeerExcel {
	
	@Autowired
    private ProductorRabbitMQ productor;

	@Scheduled(fixedDelay = 500000000L)
    public void leerExcel() {    	
    	InputStream excelStream = null;
    	List listaVentas = new ArrayList<Venta>();
        try {
        	
        	//Para formato de fechas     	
        	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        	
        	//Carga del excel para su tratamiento
        	excelStream = this.getClass().getClassLoader().getResourceAsStream("BD-Inmuebles.xls");
        	
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue;
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int r = 1; r < rows; r++) {
                hssfRow = hssfSheet.getRow(r);
                if (hssfRow == null){
                    break;
                }else{
                    System.out.print("Row: " + r + " -> ");
                    Venta venta = new Venta();
                    for (int c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                    	
                    	//Según columna hacemos un tipo de parseo

                    	switch (c){
                    	case 0:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setReferencia(Long.parseLong(cellValue.split("\\.")[0]));
                    	break;
                    	case 1:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		try {
								venta.setFechaAlta(formatter.parse(cellValue));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    	break;
                    	case 2:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setTipoInmueble(cellValue);
                    	break;
                    	case 3:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setOperacion(cellValue);
                    	break;
                    	case 4:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setProvincia(cellValue);
                    	break;
                    	case 5:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setSuperficie(Integer.parseInt(cellValue.split("\\.")[0]));
                    	break;
                    	case 6:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();                   		
                    		venta.setPrecioVenta(Long.parseLong(cellValue.split("\\.")[0]));
                    	break;
                    	case 7:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		try {
								venta.setFechaVenta(formatter.parse(cellValue));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    	break;
                    	case 8:
                    		cellValue = hssfRow.getCell(c) == null?"":hssfRow.getCell(c).toString();
                    		venta.setVendedor(cellValue);
                    	break;
                    	}
                    	
                    	
                    }
                    
                    listaVentas.add(venta);
                    
                    System.out.println(venta.toString());
                    
                    System.out.println();
                }
            }
           
           //Enviamos mensaje
           productor.enviarMensaje(listaVentas);
            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
    	
    }

}
