package br.gov.consegi.inscricao.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.consegi.inscricao.service.InscricaoService;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class InscricaoMB {

	@Inject
	private InscricaoService service;

	private String aluno;

	public void cadastrar() {
		service.cadastrar(aluno);
	}

	public List<String> getInscritos() {
		return service.getInscritos();
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getAluno() {
		return aluno;
	}
}
