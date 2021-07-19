package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import br.com.naturassp.services.dto.CompradorDTO;
import br.com.rafael.naturassp.model.Cliente;

public interface IClienteService {
	
	//public Cliente buscarPeloTelefone(String telefone);
	
	public Cliente buscaPeloCpf(String cpf);
	
	public Cliente atualizarDados(Cliente dadosOriginais);
	
	public ArrayList<Cliente> buscarPorLetra(String letra);
	
	public ArrayList<Cliente> buscarPorPalavrachave(String palavraChave);
	
	public ArrayList<Cliente> buscarTodos(); 	
	
	public ArrayList<CompradorDTO> buscarCompradores(Integer id);
	
	public ArrayList<Cliente> buscarAniversariantes(Integer mes);

}
