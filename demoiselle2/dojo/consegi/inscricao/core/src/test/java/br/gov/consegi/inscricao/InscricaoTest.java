package br.gov.consegi.inscricao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.consegi.inscricao.service.InscricaoService;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class InscricaoTest {

	private InscricaoService service;

	@Before
	public void prepara() {
		service = Beans.getReference(InscricaoService.class);
	}

	@Test
	public void cadastraComSucesso() {
		service.cadastrar("Wilson");
		Assert.assertTrue(service.estaInscrito("Wilson"));
	}

	@Test
	public void verificaAlunoNaoCadastrado() {
		Assert.assertFalse(service.estaInscrito("Super-man"));
	}

	@Test
	public void naoCadastraDuplicado() {
		service.cadastrar("Serge");
		service.cadastrar("Serge");
		Assert.assertEquals(1, service.getQtdInscritos());
	}

	@Test
	public void cadastrarComLimiteExcedido() {
		service.cadastrar("Jiraya");
		service.cadastrar("Jaspion");
		service.cadastrar("Godzilla");
		service.cadastrar("Satan Goss");
		Assert.assertFalse(service.estaInscrito("Satan Goss"));
	}

}
