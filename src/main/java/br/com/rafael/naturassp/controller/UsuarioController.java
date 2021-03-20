package br.com.rafael.naturassp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.naturassp.model.Usuario;
import br.com.rafael.naturassp.security.JWTTokenUtils;
import br.com.rafael.naturassp.security.JWToken;
import br.com.rafael.naturassp.services.IUsuarioService;

@RestController
@RequestMapping(value = "/login")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@PostMapping
	public ResponseEntity<JWToken> fazerLogin(@RequestBody Usuario dadosLogin){
		Usuario user = service.recuperarUsuario(dadosLogin);
		if(user != null) { // usuario existente preciso conferir a senha
			if(user.getSenha().equals(dadosLogin.getSenha())){
				// aqui eu crio o token do usuario
				
				JWToken token = new JWToken(JWTTokenUtils.generateToken(user));
				
				//token.setToken(token);
				
				return ResponseEntity.ok(token);
			}
			
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	
	

}
