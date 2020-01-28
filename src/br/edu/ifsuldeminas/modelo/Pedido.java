package br.edu.ifsuldeminas.modelo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double valor;
	private Double troco;
	private String obs;
	private Boolean status;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data = Calendar.getInstance();
	


	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true , mappedBy="pedido")
	private List<Carrinho> carrinho = new LinkedList<Carrinho>();
	
	@OneToOne
	private Pessoa pessoa;
	
	@OneToOne
	private Endereco end;
	
	


	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void add(Carrinho produto) {
		this.carrinho.add(produto);
	}
	
	public Double getValorTotal() {
		valor = 0.0;
		
		for(Carrinho produto : carrinho){
			valor += produto.getValor() * produto.getQtde();
		}
		
		return valor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<Carrinho> getCarrinho() {
		return carrinho;
	}

	public void setItens(List<Carrinho> carrinho) {
		this.carrinho = carrinho;
	}



	public void setCarrinho(List<Carrinho> carrinho) {
		this.carrinho = carrinho;
	}


	



	

	

	


}
