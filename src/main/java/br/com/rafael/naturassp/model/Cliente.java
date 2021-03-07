package br.com.rafael.naturassp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="tbl_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Integer idCliente;
	
	@Column(name="nome",nullable = false,length = 100)
	private String nome;
	
	@Column(name="email",nullable = false,length = 100)
	@Email
	private String email;
	
	@Column(name="telefone",nullable = false,length = 20)
	private String telefone;
	
	@Column(name="cep",nullable = false)
	private String cep;
	
	@Column(name="logradouro",nullable = false,length = 100)
	private String logradouro;
	
	@Column(name="numero",length = 100)
	private String numero;
	
	@Column(name="complemento",length = 50)
	private String complemento;
	
	@Column(name="cidade",nullable = false,length = 100)
	private String cidade;
	
	@Column(name="bairro",nullable = false,length = 100)
	private String bairro;
	
	@Column(name="estado",nullable = false,length = 2)
	private String estado;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	

}
