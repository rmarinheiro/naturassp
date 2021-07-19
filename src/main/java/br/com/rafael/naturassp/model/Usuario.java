package br.com.rafael.naturassp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id_usuario;
	
	@Column(name = "username",length = 45,nullable = false)
	private String username;
	
	@Column(name = "email",length = 50, nullable = false, unique = true)
	@NotEmpty(message = "Campo email não pode ser nuloo ou vazio")
	@Email(message = "Entre com email válido")
	private String email;
	
	@Column(name = "senha",length = 100,nullable = false)
	private String senha;
	
	@Column(name = "nome_usuario",length = 50, nullable = false)
	private String nome;
	
	@Column(name="usuario_ativo")
	private Integer ativo;

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	
	
	
	
	

}
