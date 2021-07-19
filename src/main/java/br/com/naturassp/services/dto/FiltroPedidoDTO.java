package br.com.naturassp.services.dto;

import java.time.LocalDate;

public class FiltroPedidoDTO {
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String nome;
	private Integer pago;
	private Integer entregue;
	private Integer cancelado;
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPago() {
		return pago;
	}
	public void setPago(Integer pago) {
		this.pago = pago;
	}
	public Integer getEntregue() {
		return entregue;
	}
	public void setEntregue(Integer entregue) {
		this.entregue = entregue;
	}
	public Integer getCancelado() {
		return cancelado;
	}
	public void setCancelado(Integer cancelado) {
		this.cancelado = cancelado;
	}
	
	

}
