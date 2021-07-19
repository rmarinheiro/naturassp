package br.com.rafael.naturassp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.naturassp.services.dto.FiltroPedidoDTO;
import br.com.naturassp.services.dto.VendasPorDataDTO;
import br.com.rafael.naturassp.model.Pedido;

public interface IPedidoService {
	
	public Pedido inserirNovo(Pedido novo);
	public ArrayList<Pedido> buscarPorStatus(int status);
	public Pedido mudarStatus(int idPedido,int status);
	public ArrayList<Pedido> buscarPorPedido(LocalDate inicio ,LocalDate fim);
	public ArrayList<Pedido> buscarTodos();
	public ArrayList<VendasPorDataDTO> recuperarTotaisMensal(LocalDate dataIni,LocalDate dataFim);
	public ArrayList<Pedido> filtrarPorVariosCriterios(FiltroPedidoDTO filtroPedidoDto);

}
