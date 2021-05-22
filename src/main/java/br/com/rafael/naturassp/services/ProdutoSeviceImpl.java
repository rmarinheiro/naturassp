package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.naturassp.services.exceptions.ObjectNotFoundException;
import br.com.rafael.naturassp.dao.ProdutoDao;
import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Produto;

@Component
public class ProdutoSeviceImpl implements IProdutoService {
	
	@Autowired
	private ProdutoDao produtoDao;

	@Override
	public Produto inserirNovoProduto(Produto produto) {
		try {
			
			produtoDao.save(produto);
			return produto;
			
		} catch (Exception ex) {
			System.out.println("-----ProdutoService.inserirProduto");
			ex.getMessage();
			System.out.println("----------------------------");
		}
		return null;
	}

	@Override
	public Produto alterarProduto(Produto produto) {
		try {
			produtoDao.save(produto);
			return produto;
		} catch (Exception ex) {
			System.out.println("--------ProdutoSevice.alterar-----");
			ex.getMessage();
			System.out.println("---------------------------------");
		}
		return null;
	}

	@Override
	public ArrayList<Produto> listarTodos() {
		try {
			return (ArrayList<Produto>) produtoDao.findAll();
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Erro ao listar Todos os Produtos" + e.getMessage());
			
		}
            
	}

	@Override
	public ArrayList<Produto> listarDisponiveis() {
		return produtoDao.findAllByDisponivel(1); //considero 1 como disponivel 
	}

	/*
	 * Metodo responsavel por listar produtos disponiveis por categoria
	 */
	
	@Override
	public ArrayList<Produto> listaPorCategoria(Categoria cat) {
		return produtoDao.findAllByDisponivelAndCategoria(1, cat);
	}

	/*
	 * Metodo responsavel em listar produtos indisponiveis
	 */
	@Override
	public ArrayList<Produto> listarIndisponiveis() {
		return produtoDao.findAllByDisponivel(0);
	}

	@Override
	public Produto listaProdutoPorId(int idProduto) {
		return produtoDao.findById(idProduto).orElseThrow(()-> new ObjectNotFoundException("Erro ao Buscar o Produto de id" + idProduto) );
	}

	@Override
	public ArrayList<Produto> listarPorPalavraChave(String key) {
		// TODO Auto-generated method stub
		return produtoDao.findByNomeContainingOrDetalheContaining(key,key);
	}

	@Override
	public ArrayList<Produto> listarDestaques() {
		return produtoDao.findAllByDestaque(1);
	}

}
