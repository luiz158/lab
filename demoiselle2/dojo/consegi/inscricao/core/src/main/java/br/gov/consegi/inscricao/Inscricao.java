package br.gov.consegi.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ApplicationScoped
@BusinessController
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

	public void cadastrar(String aluno) throws SalaLotadaException, AlunoDuplicadoException {
		if (inscritos.size() == config.getTamanhoSala()) {
			throw new SalaLotadaException();
		}

		if (inscritos.contains(aluno)) {
			throw new AlunoDuplicadoException();
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
