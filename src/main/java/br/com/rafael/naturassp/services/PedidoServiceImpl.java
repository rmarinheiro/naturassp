package br.com.rafael.naturassp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.naturassp.services.dto.FiltroPedidoDTO;
import br.com.naturassp.services.dto.VendasPorDataDTO;
import br.com.naturassp.services.exceptions.ObjectNotFoundException;
import br.com.rafael.naturassp.dao.ClienteDao;
import br.com.rafael.naturassp.dao.PedidoDao;
import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.model.ItemPedido;
import br.com.rafael.naturassp.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {

	@Autowired
	private PedidoDao dao;
	
	@Autowired
	private ClienteDao clienteDao;

	@Override
	public Pedido inserirNovo(Pedido novo) {
		try {
			double total = 0.0;
			for (ItemPedido item : novo.getItensPedido()) {

				item.setPreco(item.getProduto().getPreco());

				item.setPreco_total(item.getPreco() * item.getQtd_item());

				total += item.getPreco_total();
			}
			for (ItemPedido itemPedido : novo.getItensPedido()) {
				itemPedido.setPedido(novo);
			}
			novo.setStatus(Pedido.NOVO_PEDIDO);
			novo.setValorTotal(total);
			dao.save(novo);
			return novo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public ArrayList<Pedido> buscarPorStatus(int status) {
		return dao.findAllByStatusOrderByDataPedidoDesc(status);
	}

	@Override
	public Pedido mudarStatus(int idPedido, int status) {
		try {
			Pedido pedido = dao.findById(idPedido).get();
			pedido.setStatus(status);
			 dao.save(pedido);
			 return pedido;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Pedido não encontrado");
		}
		
		
	}

	@Override
	public ArrayList<Pedido> buscarPorPedido(LocalDate inicio, LocalDate fim) {
		return dao.findAllByDataPedidoBetweenOrderByDataPedidoDesc(inicio, fim);
	}

	@Override
	public ArrayList<Pedido> buscarTodos() {
		try {
			return  dao.findAllByOrderByDataPedidoDesc();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao Buscar Pedido");
		}
		
		
	}

	public ArrayList<VendasPorDataDTO> recuperarTotaisMensal(LocalDate dataIni,LocalDate dataFim) {
		
		ArrayList<VendasPorDataDTO> lista = dao.recuperarPorData(dataIni, dataFim);
		
		return lista;
	}

	@Override
	public ArrayList<Pedido> filtrarPorVariosCriterios(FiltroPedidoDTO filtroPedidoDto) {
		boolean temNome = filtroPedidoDto.getNome() != null && filtroPedidoDto.getNome().trim().length() > 0 ;
		System.out.println("Nome : " + filtroPedidoDto.getNome());
		boolean temData = filtroPedidoDto.getDataInicio() != null && filtroPedidoDto.getDataFim() != null;
		System.out.println("Data :" + temData);
		System.out.println("Data Inicio" + filtroPedidoDto.getDataInicio() + "Data Fim" + filtroPedidoDto.getDataFim());
		boolean temStatus = filtroPedidoDto.getCancelado() != 0 || 
							filtroPedidoDto.getEntregue() != 0 || filtroPedidoDto.getPago() != 0;
		System.out.println("Status : " + temStatus);
		if(!temNome && !temData && !temStatus ) {
			System.out.println("---> Condição 0 ---->");
			return dao.findAllByOrderByDataPedidoDesc();
		}
		else if(!temNome && !temData && temStatus) {
			//condição 1
			System.out.println("---> Condição 1 -->");
			return dao.findAllByStatusInOrderByDataPedidoDesc(this.getStatus(filtroPedidoDto));
		}
		
		else if(temNome && !temData && !temStatus) {
			//condição 2
			System.out.println("---> Condição 2 -->");
			ArrayList<Cliente> clientes = clienteDao.findByNomeContaining(filtroPedidoDto.getNome());
			return dao.findAllByClienteInOrderByDataPedidoDesc(clientes);
			
		}
		
		else if(temNome && !temData && temStatus) {
			//condição 3
			System.out.println("-----> Condição 3 --->");
			ArrayList<Cliente> clientes = clienteDao.findByNomeContaining(filtroPedidoDto.getNome());
			return dao.findAllByClienteInAndStatusInOrderByDataPedidoDesc(clientes, this.getStatus(filtroPedidoDto));
			
			
		}
		
		else if(temData && !temNome && !temStatus){
			//condição 4
			System.out.println("---> Condição 4 --->");
			return dao.findAllByDataPedidoBetweenOrderByDataPedidoDesc(filtroPedidoDto.getDataInicio(), filtroPedidoDto.getDataFim());
		
		}
		
		else if(temData && !temNome && temStatus) {
			//condicao 5
			System.out.println("-----> Condição 5 --->");
			return dao.findAllByDataPedidoBetweenAndStatusInOrderByDataPedidoDesc(filtroPedidoDto.getDataInicio(), filtroPedidoDto.getDataFim(), this.getStatus(filtroPedidoDto));
			
		}
		
		
		else if(temData && temNome && !temStatus) {
			//condição 6
			System.out.println("-----> Condição 6 -->");
			ArrayList<Cliente> clientes = clienteDao.findByNomeContaining(filtroPedidoDto.getNome());
			return dao.findAllByDataPedidoBetweenAndClienteInOrderByDataPedidoDesc(filtroPedidoDto.getDataInicio(), filtroPedidoDto.getDataFim(), clientes);
			
		}
		
		else if(temData && temNome && temStatus) {
			//condição 7
			System.out.println("----> Condição 7 -->");
			ArrayList<Cliente> clientes = clienteDao.findByNomeContaining(filtroPedidoDto.getNome());
			return dao.findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByDataPedidoDesc(filtroPedidoDto.getDataInicio(), filtroPedidoDto.getDataFim(), clientes, this.getStatus(filtroPedidoDto));
		}
		
		
		
		
		
		return null;
	}
	//funções utilitarias
	private Collection<Integer> getStatus(FiltroPedidoDTO filtroPedidoDTO){
		
			Collection<Integer> status = new ArrayList<Integer>();
			if(filtroPedidoDTO.getPago()!= 0) status.add(filtroPedidoDTO.getPago());
			if(filtroPedidoDTO.getCancelado()!=0) status.add(filtroPedidoDTO.getCancelado());
			if(filtroPedidoDTO.getEntregue() != 0 ) status.add(filtroPedidoDTO.getEntregue());
			return  status;
		
	}
	
	

}
