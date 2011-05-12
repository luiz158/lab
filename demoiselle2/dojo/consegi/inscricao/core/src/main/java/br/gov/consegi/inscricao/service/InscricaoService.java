package br.gov.consegi.inscricao.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.consegi.inscricao.config.InscricaoConfig;
import br.gov.consegi.inscricao.exception.AlunoJaInscritoException;
import br.gov.consegi.inscricao.exception.SalaLotadaException;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ApplicationScoped
@BusinessController
public class InscricaoService {

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

	public void cadastrar(String aluno) throws SalaLotadaException, AlunoJaInscritoException {
		if (inscritos.size() == config.getTamanhoSala()) {
			throw new SalaLotadaException();
		}

		if (inscritos.contains(aluno)) {
			throw new AlunoJaInscritoException();
		}

		inscritos.add(aluno);
		log.info(bundle.getString("cadastro.sucesso", aluno));
	}

	public void descadastrar(String aluno) {
		if (inscritos.contains(aluno)) {
			inscritos.remove(aluno);
			log.info(bundle.getString("descadastro.sucesso", aluno));
		}
	}

	public boolean estaInscrito(String aluno) {
		return inscritos.contains(aluno);
	}

	public int getQtdInscritos() {
		return inscritos.size();
	}

	public List<String> getInscritos() {
		return inscritos;
	}
}
