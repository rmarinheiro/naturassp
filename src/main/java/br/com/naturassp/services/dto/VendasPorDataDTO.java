package br.com.naturassp.services.dto;

import java.time.LocalDate;

public class VendasPorDataDTO {
	
	private double total;
	private LocalDate data;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public VendasPorDataDTO(double total, LocalDate data) {
		super();
		this.total = total;
		this.data = data;
	}
	public VendasPorDataDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	

}
