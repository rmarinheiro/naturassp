package br.com.rafael.naturassp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.naturassp.services.exceptions.ObjectNotFoundException;
import br.com.rafael.naturassp.dao.PedidoDao;
import br.com.rafael.naturassp.model.ItemPedido;
import br.com.rafael.naturassp.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {

	@Autowired
	private PedidoDao dao;

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
		return dao.findAllByDataPedidoBetween(inicio, fim);
	}

	@Override
	public ArrayList<Pedido> buscarTodos() {
		try {
			return  dao.findAllByOrderByDataPedidoDesc();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao Buscar Pedido");
		}
		
		
	}

}
