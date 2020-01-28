package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.ifsuldeminas.dao.DAO;

import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped
public class tipoController {
	private Tipo t = new Tipo();
	
	public Tipo getTipo() {
		return t;
	}

	public void gravar(){
		new DAO<Tipo>(Tipo.class).adiciona(t);
		System.out.println("Gravando Produto " + t.getTipo());
	}
	public List<Tipo> getTodosTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	public void remover(Tipo t){
		try{
		new DAO<Tipo>(Tipo.class).remove(t.getId());
	}catch(Exception e){
		FacesContext.getCurrentInstance().addMessage("tipo", new FacesMessage("Impossivel remover ,tipo já consta em um produto"));
		
	}}

}
