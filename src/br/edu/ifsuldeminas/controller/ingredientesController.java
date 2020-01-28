package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Ingredientes;


@ManagedBean
@ViewScoped

public class ingredientesController {
	
	
	private Ingredientes ingrediente= new Ingredientes();
	
	
	
	
	public Ingredientes getIngrediente() {
		return ingrediente;
	}




	public void setIngrediente(Ingredientes ingrediente) {
		this.ingrediente = ingrediente;
	}




	public void inserir(){
		if(this.ingrediente.getId() == null){
			new DAO<Ingredientes>(Ingredientes.class).adiciona(this.ingrediente);
		}
		else{
			new DAO<Ingredientes>(Ingredientes.class).atualiza(this.ingrediente);
		}
		this.ingrediente = new Ingredientes();
	}
	
	public List<Ingredientes> getTodosIngredientes(){
		return new DAO<Ingredientes>(Ingredientes.class).listaTodos();
	}
	
	public void remover(Ingredientes p){
		try{
		new DAO<Ingredientes>(Ingredientes.class).remove(p.getId());
		}catch(Exception e){
			
			FacesContext.getCurrentInstance().addMessage("ingredientes", new FacesMessage("Impossível remover, ingrediente presente de uma pizza!"));
	}
	}
	
	public void carregar(Ingredientes p){
		this.ingrediente = p;
		
	}
	

}
