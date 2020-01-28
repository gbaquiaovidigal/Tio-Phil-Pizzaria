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
		// Obtém contexto da aplicação 
		FacesContext context = event.getFacesContext();
		// Obtém o nome da página que está sendo chamada
	    String nomePagina = context.getViewRoot().getViewId();

	    // System.out.println(nomePagina);

	    // se for a página de login, o usuário pode acessar 
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

	    // Obtém usuário da sessão
	    Pessoa user = 
	    	(Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

	    // se há usuário logado, ele pode acessar as páginas
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

	    // se não há, o usuário é redirecionado para o login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/zconta2?faces-redirect=true");
	    context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador será executado na fase restore_view
	}

}
