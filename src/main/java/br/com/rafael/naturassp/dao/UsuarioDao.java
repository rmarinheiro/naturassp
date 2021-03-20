package br.com.rafael.naturassp.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByusernameOrEmail(String user,String email);
	
	

}
