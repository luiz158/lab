package br.gov.consegi.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class Inscricao {

	@Inject
	private Logger log;

	@Inject
	private InscricaoConfig config;

	@Inject
	private ResourceBundle bundle;

	private List<String> inscritos = new ArrayList<String>();

	@Startup
	public void init() {
		log.info("Ninguem chamou esse cara!");
	}

	public void cadastrar(String aluno) {
		if (inscritos.size() < config.getTamanhoSala() && !inscritos.contains(aluno)) {
			inscritos.add(aluno);
			log.info(bundle.getString("cadastro.sucesso", aluno));
		}
	}

	public boolean estaInscrito(String aluno) {
		return inscritos.contains(aluno);
	}

	public int getQtdInscritos() {
		return inscritos.size();
	}
}
