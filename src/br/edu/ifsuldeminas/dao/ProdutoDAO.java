package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Pedido;
import br.edu.ifsuldeminas.modelo.Produto;

public class ProdutoDAO {
	public Produto listaPorId(Produto p){
			 EntityManager em = JPAUtil.getEntityManager();
			 String jpql = "SELECT DISTINCT p  FROM Produto p LEFT JOIN FETCH p.ling WHERE p.id = :pId";
			 TypedQuery<Produto> query = em.createQuery(jpql,Produto.class);
			 query.setParameter("pId", p.getId());
			 p = query.getSingleResult();
			 em.close();
			 return p;
			}

	



public List<Produto> listaPizza(){
	String jpql = "SELECT DISTINCT f FROM Produto f "
			+ "LEFT JOIN FETCH f.tipo "
			+ "LEFT JOIN FETCH f.ling "
			

			+ "WHERE f.tipo.id = :pTipo";
	
	EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
	query.setParameter("pTipo",1);
	
	System.out.println(query.toString());
	
	
	List<Produto> lista = query.getResultList();
	
	em.close();
	
	return lista;
	
}


public List<Produto> listaRefri(){
	String jpql = "SELECT DISTINCT f FROM Produto f "
			+ "LEFT JOIN FETCH f.tipo "
			+ "LEFT JOIN FETCH f.ling "
			

			+ "WHERE f.tipo.id = :pTipo";
	
	EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
	query.setParameter("pTipo",2);
	
	System.out.println(query.toString());
	
	
	List<Produto> lista = query.getResultList();
	
	em.close();
	
	return lista;
	
}}