package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import br.com.rafael.naturassp.model.Usuario;

public interface IUsuarioService {
	
	public Usuario recuperarUsuario(Usuario usuario);
	public ArrayList<Usuario> recuperarTodos();
	public Usuario adicionarNovo(Usuario novo);
	public Usuario atualizarUsuario(Usuario user);
	public Usuario recuperaPeloId(Integer id);

}
