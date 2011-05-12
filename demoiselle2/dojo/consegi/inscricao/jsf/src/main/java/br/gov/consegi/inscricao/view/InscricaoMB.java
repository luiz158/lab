package br.gov.consegi.inscricao.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.consegi.inscricao.exception.AlunoJaInscritoException;
import br.gov.consegi.inscricao.exception.SalaLotadaException;
import br.gov.consegi.inscricao.service.InscricaoService;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class InscricaoMB {

	@Inject
	private InscricaoService service;

	private String aluno;

	public void cadastrar() {
		try {
			service.cadastrar(aluno);
		} catch (SalaLotadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlunoJaInscritoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
