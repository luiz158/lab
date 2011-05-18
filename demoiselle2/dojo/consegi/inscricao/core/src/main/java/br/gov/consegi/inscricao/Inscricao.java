package br.gov.consegi.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

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

	/**
	 * @param aluno
	 */
	public void cadastrar(String aluno) {
		if (aluno.trim().isEmpty()) {
			throw new CampoObrigatorioException("nome");
		}
		
		if (verificaAlunoInscrito(aluno)) {
			throw new AlunoDuplicadoException(aluno);
		}

		if (isSalaLotada()) {
			throw new SalaLotadaException();
		}

		inscritos.add(aluno);
		log.info(bundle.getString("cadastro.sucesso", aluno));
	}

	public void descadastrar(String aluno) {
		if (verificaAlunoInscrito(aluno)) {
			inscritos.remove(aluno);
			log.info(bundle.getString("descadastro.sucesso", aluno));
		} else {
			throw new AlunoNaoCadastradoException(aluno);
		}
	}

	public boolean verificaAlunoInscrito(String aluno) {
		return inscritos.contains(aluno);
	}

	public boolean isSalaLotada() {
		return inscritos.size() == config.getTamanhoSala();
	}

	public List<String> getInscritos() {
		return new ArrayList<String>(inscritos);
	}

}
