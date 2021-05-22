package br.com.rafael.naturassp.services;

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
			if (user.getSenha().equals(usuario.getSenha())) {
				user.setSenha(null);
				return user;
			}
		return null;
	}

}
