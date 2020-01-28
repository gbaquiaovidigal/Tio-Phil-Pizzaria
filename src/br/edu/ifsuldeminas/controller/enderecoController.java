package br.edu.ifsuldeminas.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Endereco;



@ManagedBean
@ViewScoped
public class enderecoController {
	private Endereco end = new Endereco();
	
	
	public void inserir(Endereco END){
	
		if(END.getId() == null){
			new DAO<Endereco>(Endereco.class).adiciona(END);
		}
		else{
			new DAO<Endereco>(Endereco.class).atualiza(END);
		}
		this.end = new Endereco();
	}
	
	public List<Endereco> getTodosenderecos(){
		return new DAO<Endereco>(Endereco.class).listaTodos();
	}
	
	public void remover(Endereco p){
		new DAO<Endereco>(Endereco.class).remove(p.getId());
	}
	
	public void carregar(Endereco p){
		this.end = p;
		
	}






	public Endereco getEnd() {
		return end;
	}






	public void setEnd(Endereco end) {
		this.end = end;
	}



	
	



}



