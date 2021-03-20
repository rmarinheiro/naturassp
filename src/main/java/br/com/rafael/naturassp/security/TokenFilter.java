package br.com.rafael.naturassp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*
		 * segredo do sucesso
		 * request que não precisam de autenticação não levam em conta um item do cabeçalho
		 * - Authorization
		 * requisições que precisam de autenticação, vão precisar de uma informação de autorização
		 * - se o token é valido monto um cabeçalho de autorização e encaminho a requisição
		 */
		System.out.println("Requisição passou pelo filtro");
		/*
		 * A mágica acontece aqui
		 */
		
		if(request.getHeader("Authorization") != null) {
			//se tiver cabeçalho com token preciso decodifica
			// se for valido vai pro contexto da requisição um objeto que representa o token
			//senão vai null
			Authentication auth = JWTTokenUtils.decodeToken(request);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
