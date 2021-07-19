package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rafael.naturassp.dao.UsuarioDao;
import br.com.rafael.naturassp.model.Usuario;

@Component
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Usuario recuperarUsuario(Usuario usuario) {

		Usuario user = usuarioDao.findByusernameOrEmail(usuario.getUsername(), usuario.getEmail());
		if (user != null)
			if (user.getSenha().equals(usuario.getSenha()) && user.getAtivo() == 1) {
				user.setSenha(null);
				return user;
			}
		return null;
	}

	@Override
	public ArrayList<Usuario> recuperarTodos() {
		return (ArrayList<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario adicionarNovo(Usuario novo) {
		if(novo.getNome().length()>0 && novo.getEmail().length() > 0 && novo.getSenha().length() > 0 && novo.getUsername().length() > 0) {
			novo.setAtivo(1);
			try {
				usuarioDao.save(novo);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
		return novo;
	}

	@Override
	public Usuario atualizarUsuario(Usuario user) {
		
		try {
			usuarioDao.save(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuario recuperaPeloId(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

}
