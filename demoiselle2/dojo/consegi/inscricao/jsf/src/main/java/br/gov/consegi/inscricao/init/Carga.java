package br.gov.consegi.inscricao.init;

import javax.inject.Inject;

import br.gov.consegi.inscricao.service.InscricaoService;
import br.gov.frameworkdemoiselle.annotation.Startup;

public class Carga {

	@Inject
	private InscricaoService service;

	@Startup
	public void cargaInicial() {
		service.cadastrar("Wilson");
		service.cadastrar("ZyC");
	}
}
