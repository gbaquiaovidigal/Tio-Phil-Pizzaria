package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Pedido;
import br.edu.ifsuldeminas.modelo.Pessoa;




public class PedidoDAO {
	public Pedido listaPorId(Pedido c) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT c FROM Pedido c LEFT JOIN FETCH c.carrinho WHERE c.id = :pId";
		
		TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
		query.setParameter("pId", c.getId());
		
		c = query.getSingleResult();
		
		em.close();
		
		return c;
	}
	
	
	
	
	public List<Pedido> pedidosativos(){
        
		
		String jpql = "SELECT DISTINCT f FROM Pedido f "
				+ "LEFT JOIN FETCH f.end "
				+ "LEFT JOIN FETCH f.pessoa "
				+ "LEFT JOIN FETCH f.carrinho "

				+ "WHERE f.status = :pStatus";
				
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
		query.setParameter("pStatus", true);
		
		System.out.println(query.toString());
		
		
		List<Pedido> lista = query.getResultList();
		
		em.close();
		
		return lista;
	
	}
	
}
