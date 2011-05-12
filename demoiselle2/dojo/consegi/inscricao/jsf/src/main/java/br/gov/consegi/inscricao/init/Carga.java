package br.gov.consegi.inscricao.init;

import javax.inject.Inject;

import br.gov.consegi.inscricao.exception.AlunoJaInscritoException;
import br.gov.consegi.inscricao.exception.SalaLotadaException;
import br.gov.consegi.inscricao.service.InscricaoService;
import br.gov.frameworkdemoiselle.annotation.Startup;

public class Carga {

	@Inject
	private InscricaoService service;

	@Startup
	public void cargaInicial() {
		try {
			service.cadastrar("Wilson");
			service.cadastrar("ZyC");
		} catch (SalaLotadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlunoJaInscritoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
