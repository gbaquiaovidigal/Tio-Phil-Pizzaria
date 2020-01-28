package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Carrinho;
import br.edu.ifsuldeminas.modelo.Pedido;

public class CarrinhoDAO {
	

		
		public List<Carrinho> listaPorPedido (Pedido c) {
			EntityManager em = JPAUtil.getEntityManager();
			
			String jpql = "SELECT i FROM Carrinho i WHERE i.pedido = :pPedido";
			
			TypedQuery<Carrinho> query = em.createQuery(jpql,Carrinho.class);
			query.setParameter("pPedido", c);
			
			List<Carrinho> lista = query.getResultList();
			
			em.close();
			
			return lista;
		}

	


}
