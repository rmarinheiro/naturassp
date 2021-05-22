package br.com.rafael.naturassp.services;

import br.com.rafael.naturassp.model.Cliente;

public interface IClienteService {
	
	//public Cliente buscarPeloTelefone(String telefone);
	
	public Cliente buscaPeloCpf(String cpf);
	
	public Cliente atualizarDados(Cliente dadosOriginais);

}
