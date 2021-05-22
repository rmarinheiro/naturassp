package br.com.rafael.naturassp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.naturassp.services.exceptions.ObjectNotFoundException;
import br.com.rafael.naturassp.dao.ClienteDao;
import br.com.rafael.naturassp.model.Cliente;

@Component
public class ClienteSeviceImpl implements IClienteService {
	
	@Autowired
	private ClienteDao clienteDao;

	@Override
	public Cliente buscaPeloCpf(String cpf) {
		try {
			return  clienteDao.findByCpf(cpf);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Cliente com CPF " + cpf + "não encontrado");
		}
		
		
	}

	@Override
	public Cliente atualizarDados(Cliente dadosOriginais) {
		try {
			return clienteDao.save(dadosOriginais);
		} catch (Exception e) {
			System.out.println("Erro ao salvar o cliente" + e.getMessage());
		}
		
		return null;
		
		
	}
	
	

}
