package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.edu.ifsuldeminas.dao.CarrinhoDAO;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.PedidoDAO;
import br.edu.ifsuldeminas.modelo.Carrinho;
import br.edu.ifsuldeminas.modelo.Endereco;
import br.edu.ifsuldeminas.modelo.Pedido;
import br.edu.ifsuldeminas.modelo.Pessoa;
import br.edu.ifsuldeminas.modelo.Produto;


@ManagedBean
@ViewScoped
public class pedidoController {
	private Pedido pedido = new Pedido();
	private Integer qtde;
	private Integer endId;
	private Integer produtoId;
	private Integer pagId;
	private List<Carrinho> carrinho;
    private boolean render;
	private String login , senha;
	
	
	
	
	

	
	



	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public List<Carrinho> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<Carrinho> carrinho) {
		this.carrinho = carrinho;
	}

	public Integer getEndId() {
		return endId;
	}

	public void setEndId(Integer endId) {
		this.endId = endId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Integer getQtde() {
		return qtde;
	}
	
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	
	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public List<Produto> getTodosProdutos(){
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	

	public void gravarItem(){
		
		try {
			
		
			// TODO: handle exception
		
		Carrinho carrinho = new Carrinho();
		
	   
		Produto p = new DAO<Produto>(Produto.class).listaPorId(produtoId);
		carrinho.setProduto(p);
		carrinho.setQtde(qtde);
		carrinho.setValor(p.getValor());
		carrinho.setPedido(pedido);
		pedido.add(carrinho);
	   
		
		
		
		
		qtde = null;
		produtoId = null;
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage("zpedido", new FacesMessage("Selecione um Produto!"));
		
	}}
	
	public List<Carrinho> getItensDoPedido() {
		return pedido.getCarrinho();
	}
	
	public List<Carrinho> getItensDoPedido(Pedido p) {
		for (Carrinho pro : p.getCarrinho()) {
			System.out.println("\n\n\n>>>>>" + pro.getProduto().getNome() + "<<<<<\n");
		}
		return p.getCarrinho();
	}
	public void removerItem(Carrinho item){
		pedido.getCarrinho().remove(item);
		
	}
	
	public void gravar(){
		try {
			
		
		FacesContext context = FacesContext.getCurrentInstance();
		Pessoa cliente = (Pessoa)context.getExternalContext().getSessionMap().get("usuariologado");
		System.out.println("\n\n >>>>>>>>>>>" + this.endId + "<<<<<<<<<<<<<<\n\n");
		Endereco end = new DAO<Endereco>(Endereco.class).listaPorId(endId);
        System.out.println("\n\n >>>>>>>>>>>" + end.getRua() + "<<<<<<<<<<<<<<\n\n");
        
		pedido.setPessoa(cliente);
		pedido.setStatus(true);
		pedido.setEnd(end);
        cliente.getPeds().add(pedido);
		
		
		if (this.pedido.getId() == null) {
			new DAO<Pedido>(Pedido.class).adiciona(pedido);
		} else {
			new DAO<Pedido>(Pedido.class).atualiza(pedido);
		}
		
		this.pedido = new Pedido();
		this.endId = null;
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("zpedido", new FacesMessage("Selecione um Endereco!"));
			
		}
	}
	
	private void atualiza() {
		List<Carrinho> listaItens =  new CarrinhoDAO().listaPorPedido(pedido);
		DAO<Carrinho> d = new DAO<Carrinho>(Carrinho.class);
		
		for (Carrinho i : listaItens){
			d.remove(i.getId());
		}
		new DAO<Pedido>(Pedido.class).atualiza(pedido);
		
	}

	public List<Pedido> getTodosPedidos(){
		return new DAO<Pedido>(Pedido.class).listaTodos();
	}
	
	public void remover(Pedido c){
		new DAO<Pedido>(Pedido.class).remove(c.getId());
	}
	
	public void carregar(Pedido c){
		c =  new PedidoDAO().listaPorId(c);
		pedido = c;
		
	}
	
	
	public List<Endereco> getTodosEnderecos(){
		FacesContext context = FacesContext.getCurrentInstance();
		Pessoa p = (Pessoa)context.getExternalContext().getSessionMap().get("usuariologado");

		
        return p.getEnds();
		
	}
	
	public List<Pedido> getPedidosAtivos(){
		
		List<Pedido> peds = new PedidoDAO().pedidosativos();
		
		return peds;
		
	}
	
	public void finalizar(Pedido p){
		p.setStatus(false);
		new DAO<Pedido>(Pedido.class).atualiza(p);
		
	}
	
	public void exibir(Pedido p){
		for (Carrinho car : p.getCarrinho()) {
			System.out.println("\n\n>>>" + car.getProduto().getNome()  + car.getQtde());
			
		}
		
		this.carrinho = p.getCarrinho();
		this.render = true;
	
	}
	
	public List<Pedido> getMeusPedidos(){
		FacesContext context = FacesContext.getCurrentInstance();
		Pessoa p = (Pessoa)context.getExternalContext().getSessionMap().get("usuariologado");
		return p.getPeds();
		
		
	}
	
	public boolean mostrarItens(){
		return this.render;
	}
	
	
	public List<Carrinho> getItensdoPedido(){
		return this.carrinho;
	}


	


	



}
