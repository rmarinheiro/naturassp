package br.com.rafael.naturassp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Integer id;
	
	@Column(name = "nome_produto", length = 100 , nullable = false)
	@Size(min = 2 , max = 100, message = "O Nome do produto deve ter entre 2 à 100 caracteres")
	private String nome;
	
	@Column(name = "descricao_produto", length = 200,nullable = false)
	private String detalhe;
	
	@Column(name="link_produto",length = 255, nullable = false)
	private String link;
	
	@Column(name="preco")
	@Positive(message = "O campo preço deve ser positivo")
	private Double preco;
	
	@Column(name="disponivel", length = 1, nullable = false)
	private Integer disponivel;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Integer disponivel) {
		this.disponivel = disponivel;
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
