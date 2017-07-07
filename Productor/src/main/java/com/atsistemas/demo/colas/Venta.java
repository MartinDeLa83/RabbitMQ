package com.atsistemas.demo.colas;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

public class Venta implements Serializable{
	

	private long referencia;

	private Date fechaAlta;

	private String tipoInmueble;

	private String operacion;

	private int superficie;

	private long precioVenta;

	private Date fechaVenta;

	private String vendedor;

	private String provincia;
	
	public Venta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venta(long referencia, Date fechaAlta, String tipoInmueble, String operacion, int superficie,
			long precioVenta, Date fechaVenta, String vendedor, String provincia) {
		super();
		this.referencia = referencia;
		this.fechaAlta = fechaAlta;
		this.tipoInmueble = tipoInmueble;
		this.operacion = operacion;
		this.superficie = superficie;
		this.precioVenta = precioVenta;
		this.fechaVenta = fechaVenta;
		this.vendedor = vendedor;
		this.provincia = provincia;
	}

	public long getReferencia() {
		return referencia;
	}

	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public long getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(long precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (referencia ^ (referencia >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		if (referencia != other.referencia)
			return false;
		return true;
	}

	
	
	
}
	