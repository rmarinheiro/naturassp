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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.naturassp.services.dto.PathDTO;
import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Produto;
import br.com.rafael.naturassp.services.IProdutoService;
import br.com.rafael.naturassp.services.IUploadService;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private IProdutoService service;
	
	@Autowired
	private IUploadService upload;
	
	@PostMapping
	public ResponseEntity<Produto> novoProduto(@Valid @RequestBody Produto produto){
		try {
			service.inserirNovoProduto(produto);
			return ResponseEntity.status(201).body(produto);
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return ResponseEntity.badRequest().build();	
	}
	
	@PostMapping("/upload")
	public ResponseEntity<PathDTO> uploadFoto(@RequestParam(name="arquivo") MultipartFile arquivo){
		String path = upload.uploadService(arquivo);
		if(path!= null) {
			PathDTO pathDTO = new PathDTO();
			pathDTO.setPathFile(path);
			return ResponseEntity.status(201).body(pathDTO);
			
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<Produto>> recuperarTodosDisponiveis(){
		return ResponseEntity.ok(service.listarDisponiveis());
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<ArrayList<Produto>> recuperarPorCategoria(@PathVariable(name="id") int idCateg){
		Categoria cat = new Categoria();
		cat.setId(idCateg);
		return ResponseEntity.ok(service.listaPorCategoria(cat));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> recuperarPorId(@PathVariable(name="id") int idProduto){
		Produto prod=  service.listaProdutoPorId(idProduto);
		if(prod!=null) {
			return ResponseEntity.ok(prod);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/busca")
	public ResponseEntity<ArrayList<Produto>> buscaPorPalavraChave(@RequestParam (name = "key") String key){
		System.out.println("key + " + key);
		if(key != null ) {
			return ResponseEntity.ok(service.listarPorPalavraChave(key));
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping("/recuperarTodos")
	public ResponseEntity<ArrayList<Produto>> recuperarTodos(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@PutMapping("/atualizar/{idProduto}")
	public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto atual , @PathVariable int idProduto){
		try {
			if(idProduto != atual.getId()) {
				return ResponseEntity.badRequest().build();
			}
			Produto prod = service.alterarProduto(atual);
			return ResponseEntity.ok(prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

}
