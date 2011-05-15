package br.gov.consegi.inscricao;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class InscricaoMB {

	@Inject
	private Inscricao inscricao;

	private String aluno;

	public void cadastrar() throws SalaLotadaException, AlunoDuplicadoException {
		inscricao.cadastrar(aluno);
	}

	public List<String> getInscritos() {
		return inscricao.getInscritos();
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getAluno() {
		return aluno;
	}
}
