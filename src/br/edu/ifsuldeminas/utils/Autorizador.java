package br.edu.ifsuldeminas.utils;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.ifsuldeminas.modelo.Pessoa;



public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obt�m contexto da aplica��o 
		FacesContext context = event.getFacesContext();
		// Obt�m o nome da p�gina que est� sendo chamada
	    String nomePagina = context.getViewRoot().getViewId();

	    // System.out.println(nomePagina);

	    // se for a p�gina de login, o usu�rio pode acessar 
	    if ("/zconta.xhtml".equals(nomePagina)) {
	        return;
	    }
	    
    	if (nomePagina.equals("/zindex.xhtml")) {
    		return;
    	}
    	
    	else if (nomePagina.equals("/zsobre.xhtml")) {
    		return;
    	}
    	
    	else if (nomePagina.equals("/zcardapio.xhtml")) {
    		return;
    	}
    	
    	else if (nomePagina.equals("/zconta2.xhtml")) {
    		return;
    	}

	    // Obt�m usu�rio da sess�o
	    Pessoa user = 
	    	(Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

	    // se h� usu�rio logado, ele pode acessar as p�ginas
	    if(user!= null) {
	    	

	    	if (nomePagina.equals("/zenderecos.xhtml")) {
	    		return;
	    	}
	    	
	    	else if (nomePagina.equals("/zpedido.xhtml")) {
	    		return;
	    	}
	    	
	    	else if (nomePagina.equals("/zminha-conta.xhtml")) {
	    		return;
	    	}
	    	

	    	
	    	Boolean permit = user.getPermissao();
	  
	    	if (permit == true){
	    		return;
	    	}
	    	
	    	NavigationHandler handler = context.getApplication().getNavigationHandler();
		    handler.handleNavigation(context, null, "/zindex?faces-redirect=true");
		    context.renderResponse();
	        return;
	    }

	    // se n�o h�, o usu�rio � redirecionado para o login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/zconta2?faces-redirect=true");
	    context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador ser� executado na fase restore_view
	}

}
