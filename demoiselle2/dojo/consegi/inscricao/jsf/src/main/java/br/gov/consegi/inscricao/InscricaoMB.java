package br.gov.consegi.inscricao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class InscricaoMB {

	@Inject
	private Inscricao inscricao;

	private String aluno;

	private Map<String, Boolean> selection = new HashMap<String, Boolean>();

	public void cadastrar() {
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

	public Map<String, Boolean> getSelection() {
		return selection;
	}

	public void setSelection(Map<String, Boolean> selection) {
		this.selection = selection;
	}

	public void excluir() {
		boolean delete;
		for (Iterator<String> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			String aluno = iter.next();
			delete = getSelection().get(aluno);

			if (delete) {
				inscricao.descadastrar(aluno);
				iter.remove();
			}
		}

	}

}
