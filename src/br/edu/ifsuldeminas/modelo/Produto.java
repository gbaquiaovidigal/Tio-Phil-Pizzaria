package br.edu.ifsuldeminas.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; //chave primaria e autoincremento
	private String nome,tamanho;
    private Double valor;
	
	@OneToOne
	private Tipo tipo;
	
	@ManyToMany
	private List<Ingredientes> ling = new LinkedList<Ingredientes>();
	

	
	
	
	public void addIngredientes(Ingredientes ing){
		ling.add(ing);
	}
	
	public List<Ingredientes> getLing() {
		return ling;
	}
	public void setLing(List<Ingredientes> ling) {
		this.ling = ling;
	}
	public Integer getId() {
		return id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	
	

}
