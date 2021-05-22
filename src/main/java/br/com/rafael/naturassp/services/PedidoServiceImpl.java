package br.com.rafael.naturassp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
			novo.setValorTotal(total);
			dao.save(novo);
			return novo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
