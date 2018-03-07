package org.com.dm.service.transformation;

public class Cronograma {
	private String id_cronograma;
	private Integer nro_cuota;
	private Double cuota;
	private Double amortizacion;
	private Double interes;
	private Double capital;
	private String fecha_vencimiento;

	public String getId_cronograma() {
		return id_cronograma;
	}

	public void setId_cronograma(String id_cronograma) {
		this.id_cronograma = id_cronograma;
	}

	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Integer getNro_cuota() {
		return nro_cuota;
	}

	public void setNro_cuota(Integer nro_cuota) {
		this.nro_cuota = nro_cuota;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Double getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(Double amortizacion) {
		this.amortizacion = amortizacion;
	}

	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	

}
