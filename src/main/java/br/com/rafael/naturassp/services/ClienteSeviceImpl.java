package br.com.rafael.naturassp.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.naturassp.services.dto.CompradorDTO;
import br.com.naturassp.services.dto.VendasPorDataDTO;
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

	@Override
	public ArrayList<Cliente> buscarPorLetra(String letra) {
		return clienteDao.findByNomeStartsWith(letra);
	}

	@Override
	public ArrayList<Cliente> buscarPorPalavrachave(String palavraChave) {
		return clienteDao.findByNomeContaining(palavraChave);
	}

	@Override
	public ArrayList<Cliente> buscarTodos() {
		return clienteDao.findByOrderByNomeAsc();
	}

	@Override
	public ArrayList<CompradorDTO> buscarCompradores(Integer idProduto) {
		ArrayList<CompradorDTO> lista = new ArrayList<>();
		for(Object[] values: clienteDao.recuperarCompradores(idProduto)) {
			lista.add(new CompradorDTO(values[0].toString(),values[1].toString(),values[2].toString())	);
		}
		return lista;
	}

	@Override
	public ArrayList<Cliente> buscarAniversariantes(Integer mes) {
		try {
			return clienteDao.recuperarAniversariantes(mes);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Nenhum Aniversariante encontrado no mes " + mes);
		}
		
	}
	
	

}
