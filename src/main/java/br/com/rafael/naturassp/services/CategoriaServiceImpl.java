package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.naturassp.services.exceptions.ObjectNotFoundException;
import br.com.rafael.naturassp.dao.CategoriaDao;
import br.com.rafael.naturassp.model.Categoria;

@Component
public class CategoriaServiceImpl  implements ICategoriaService{
	
	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	public Categoria inserirNovaCategoria(Categoria categoria) {
		try {
			if(categoria != null && categoria.getNome().length() > 0) {
				categoriaDao.save(categoria);
				return categoria;
			}
			
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG = "+ e.getMessage());
		}catch(Exception ex) {
			System.out.println("DEBUG="+ ex.getMessage());
		}
		return null;
	}

	@Override
	public Categoria alterarCategoria(Categoria categoria) {
		try {
			if(categoria.getId()!=null && categoria.getNome()!= null && categoria.getNome().length() > 0) {
				return categoriaDao.save(categoria);
			}
			
		} catch (Exception ex) {
			System.out.println("DEBUG ="+ ex.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Categoria> recuperarTodasCategorias() {
		return (ArrayList<Categoria>) categoriaDao.findAll();
	}

	@Override
	public ArrayList<Categoria> recuperarPorPalavraChave(String palavraChave) {
		try {
			if(palavraChave != null) {
				return categoriaDao.findByNomeContaining(palavraChave);
			}
			
		} catch (Exception e) {
			throw new ObjectNotFoundException("Objeto não encontrado pela palavra chave" + palavraChave );
		}
		
		return null;
		
	}

	@Override
	public Categoria recuperarPorId(int id) {
		try {
			if(id != 0) {
				return categoriaDao.findById(id).get();
			}
		} catch (Exception e) {
			throw new ObjectNotFoundException("Objeto da Categoria com Id " + id +" não encontrado");
		}
		return null;
	}
	
	
	

	@Override
	public ArrayList<Categoria> recuperarTodasOrdenadasPorId() {
		return categoriaDao.findAllByOrderById();
	}
	
	

}
