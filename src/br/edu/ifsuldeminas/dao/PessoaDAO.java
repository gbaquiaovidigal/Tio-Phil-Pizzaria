package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.loader.entity.EntityLoader;


import br.edu.ifsuldeminas.modelo.Pessoa;
import br.edu.ifsuldeminas.utils.Utils;

public class PessoaDAO {
	


		public Pessoa listaPorId(Pessoa p) {
			EntityManager em = JPAUtil.getEntityManager();
		    String jpql = "SELECT DISTINCT p FROM Pessoa p LEFT JOIN FETCH p.end WHERE p.id = :pId";
		    TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
			query.setParameter("pId", p.getId());
			p = query.getSingleResult();
			em.close();
		    return p;	
		}
		
		public Pessoa buscarPessoa(String nome){
            Pessoa usuario;
			
			String jpql = "SELECT DISTINCT f FROM Pessoa f "
					+ "LEFT JOIN FETCH f.ends "
					+ "WHERE f.nome = :pNome";
					
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
			query.setParameter("pNome", nome);
			
			System.out.println(query.toString());
			
			try {
				usuario = query.getSingleResult();
		    } catch (NoResultException ex) {
		        usuario = null;
		    }
			
			em.close();
			
			return usuario;
		}
		
		
		
		public Pessoa buscarCpf(String cpf){
            Pessoa usuario;
			
			String jpql = "SELECT DISTINCT f FROM Pessoa f "
					+ "LEFT JOIN FETCH f.ends "
					+ "WHERE f.cpf = :pCpf";
					
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
			query.setParameter("pCpf", cpf);
			
			System.out.println(query.toString());
			
			try {
				usuario = query.getSingleResult();
		    } catch (NoResultException ex) {
		        usuario = null;
		    }
			
			em.close();
			
			return usuario;
		}
			
		
		
		public void inserirId(Integer id){
		   EntityManager em = JPAUtil.getEntityManager();
           Query query = (Query) em.createNativeQuery("INSERT INTO Endereco "
           + "(ID,BAIRRO,CEP,CIDADE,ESTADO,NUMERO,REFERENCIA,RUA,PESSOA_ID " +
		   " VALUES(?,?,?,?,?,?,?,?,?)");
           ((javax.persistence.Query) query).setParameter(9, id);
           ((javax.persistence.Query) query).executeUpdate();

			
			
		}
		

		

		public Pessoa buscarPorEmailESenha(String login, String senha) {
			Pessoa usuario;
			
			String jpql = "SELECT DISTINCT f FROM Pessoa f "
					+ "LEFT JOIN FETCH f.ends "
					+ "WHERE f.login = :pLogin AND f.senha = :pSenha";
					
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
			query.setParameter("pLogin", login);
			query.setParameter("pSenha", Utils.toMD5(senha));
			
			try {
				usuario = query.getSingleResult();
		    } catch (NoResultException ex) {
		        usuario = null;
		    }
			
			em.close();
			
			return usuario;
		}}


