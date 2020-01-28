package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;

import br.edu.ifsuldeminas.dao.ProdutoDAO;
import br.edu.ifsuldeminas.modelo.Ingredientes;

import br.edu.ifsuldeminas.modelo.Produto;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped

public class produtoController {
	private Produto produto = new Produto();
	private Integer tipoId;
	private Integer ingId;
	
	public List<Ingredientes> getIngredientes(){
		return produto.getLing();
	}
	
	public void remover(Ingredientes i){
		this.produto.getLing().remove(i);
	}
	
	
	
	  
	public Integer getIngId() {
		return ingId;
	}

	public void setIngId(Integer ingId) {
		this.ingId = ingId;
	}

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void gravarIngrediente(){
		Ingredientes i = new DAO<Ingredientes>(Ingredientes.class).listaPorId(ingId);
		produto.addIngredientes(i);
		ingId = null;
		
	}
	
	public void carregar(Produto p){
		this.tipoId = p.getTipo().getId();
		p = new ProdutoDAO().listaPorId(p);
		produto = p;
	}

	public void gravar(){
		Tipo t = new DAO<Tipo>(Tipo.class).listaPorId(this.tipoId);
		produto.setTipo(t);
	    if(this.produto.getId() == null){
		new DAO<Produto>(Produto.class).adiciona(produto);
		}
		else{
		new DAO<Produto>(Produto.class).atualiza(produto);	
		}
		this.produto = new Produto();
		this.tipoId = 0;
		System.out.println("Gravando Produto " + produto.getNome());
	}
	public List<Produto> getTodosProdutos(){
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	public List<Tipo> getTodosTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	public void remover(Produto p){
		try {
	
		new DAO<Produto>(Produto.class).remove(p.getId());
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("ingredientes", new FacesMessage("Impossível remover, produto já consta em um pedido!"));
		}
	}
	
	public List<Ingredientes>getIngredientesProduto(){
		return produto.getLing();
	}
	
	public List<Ingredientes> getTodosIngredientes(){
		return new DAO<Ingredientes>(Ingredientes.class).listaTodos();
	}
	
	public List<Produto> getTodasPizzas(){
		return new ProdutoDAO().listaPizza();
	}
	
	public List<Produto> getTodosRefri(){
		return new ProdutoDAO().listaRefri();
	}

}
