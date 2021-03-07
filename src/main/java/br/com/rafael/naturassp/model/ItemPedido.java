package br.com.rafael.naturassp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table
@Entity(name="tbl_itempedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_itempedido;
	
	@Column(name="qtd_item",nullable = false)
	private Integer qtd_item;
	
	@Column(name="preco_unitario",nullable = false)
	private Double preco;
	
	
	@Column(name="preco_total",nullable = false)
	private Double preco_total;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	@JsonIgnoreProperties("itensPedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	public Integer getId_itempedido() {
		return id_itempedido;
	}

	public void setId_itempedido(Integer id_itempedido) {
		this.id_itempedido = id_itempedido;
	}

	public Integer getQtd_item() {
		return qtd_item;
	}

	public void setQtd_item(Integer qtd_item) {
		this.qtd_item = qtd_item;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	

	public Double getPreco_total() {
		return preco_total;
	}

	public void setPreco_total(Double preco_total) {
		this.preco_total = preco_total;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedido [id_itempedido=" + id_itempedido + ", qtd_item=" + qtd_item + ", preco=" + preco
				+ ", preco_total=" + preco_total + ", pedido=" + pedido + ", produto=" + produto.getId() + "]";
	}
	
	

}
