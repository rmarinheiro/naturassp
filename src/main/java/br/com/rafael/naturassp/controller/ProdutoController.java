package br.com.rafael.naturassp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rafael.naturassp.model.Produto;
import br.com.rafael.naturassp.services.IProdutoService;
import br.com.rafael.naturassp.services.IUploadService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private IProdutoService service;
	
	@Autowired
	private IUploadService upload;
	
	@PostMapping
	public ResponseEntity<Produto> novoProduto(@RequestBody Produto produto){
		try {
			service.inserirNovoProduto(produto);
			return ResponseEntity.status(201).body(produto);
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return ResponseEntity.badRequest().build();	
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFoto(@RequestParam(name="arquivo") MultipartFile arquivo){
		String path = upload.uploadService(arquivo);
		if(path!= null) {
			return ResponseEntity.status(201).body(path);
			
		}
		
		return ResponseEntity.badRequest().build();
	}
	

}
