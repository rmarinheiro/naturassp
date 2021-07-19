package br.com.rafael.naturassp.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.naturassp.model.Usuario;
import br.com.rafael.naturassp.security.JWTTokenUtils;
import br.com.rafael.naturassp.security.JWToken;
import br.com.rafael.naturassp.services.IUsuarioService;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@PostMapping
	public ResponseEntity<JWToken> fazerLogin(@RequestBody Usuario dadosLogin){
		Usuario user = service.recuperarUsuario(dadosLogin);
		if(user != null) { // usuario existente preciso conferir a senha
			
				// aqui eu crio o token do usuario
				
				JWToken token = new JWToken(JWTTokenUtils.generateToken(user));
				
				//token.setToken(token);
				
				return ResponseEntity.ok(token);
			}
			
		
		return ResponseEntity.status(403).build();
		
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<ArrayList<Usuario>> recuperarTodos(){
		return ResponseEntity.ok(service.recuperarTodos());
	}
	
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> adicionarNovo(@RequestBody @Valid Usuario usuario){
		Usuario res = service.adicionarNovo(usuario);
		if(res != null) {
			return ResponseEntity.status(201).body(res);
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> alterarUsuario(@RequestBody @Valid Usuario usuario, @PathVariable int id){
		usuario.setId_usuario(id);
		Usuario res = service.atualizarUsuario(usuario);
		if(res!= null) {
			return ResponseEntity.ok(res);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("usuarios/{id}")
	public ResponseEntity<Usuario>recuperarPeloId(@PathVariable Integer id){
		Usuario res = service.recuperaPeloId(id);
		if(res != null) {
			return ResponseEntity.ok(res);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
