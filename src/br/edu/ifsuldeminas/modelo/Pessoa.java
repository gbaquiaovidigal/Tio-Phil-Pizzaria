package br.edu.ifsuldeminas.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;	
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String nome, email, senha,cpf,telefone;
private Boolean permissao;

@Column(unique = true)
private String login;


@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true,mappedBy="pessoa")
private List<Endereco> ends = new LinkedList<Endereco>();


@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval = true,mappedBy="pessoa")
private List<Pedido> peds = new LinkedList<Pedido>();


@Temporal(TemporalType.TIMESTAMP)
private java.util.Date nasc; 


public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public List<Endereco> getEnds() {
	return ends;
}
public void setEnds(List<Endereco> ends) {
	this.ends = ends;
}
public Integer getId() {
	return id;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public String getTelefone() {
	return telefone;
}

public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public List<Pedido> getPeds() {
	return peds;
}
public void setPeds(List<Pedido> peds) {
	this.peds = peds;
}

public Boolean getPermissao() {
	return permissao;
}
public void setPermissao(Boolean permissao) {
	this.permissao = permissao;
}
public java.util.Date getNasc() {
	return nasc;
}
public void setNasc(java.util.Date nasc) {
	this.nasc = nasc;
}







}
