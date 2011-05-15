package br.gov.consegi.inscricao;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class InscricaoTest {

	@Inject
	private Logger logger;

	private Inscricao service;

	@Before
	public void prepara() {
		service = Beans.getReference(Inscricao.class);
	}

	@Test
	public void cadastraComSucesso() throws Exception {
		service.cadastrar("Wilson");
		Assert.assertTrue(service.estaInscrito("Wilson"));
	}

	@Test
	public void verificaAlunoNaoCadastrado() {
		Assert.assertFalse(service.estaInscrito("Super-man"));
	}

	@Test
	public void naoCadastraDuplicado() throws SalaLotadaException {
		try {
			service.cadastrar("Serge");
			service.cadastrar("Serge");
			Assert.assertEquals(1, service.getQtdInscritos());

			fail("Deveria dar erro");
		} catch (AlunoDuplicadoException cause) {
			logger.info(cause.getMessage());
		}
	}

	@Test
	public void cadastrarComLimiteExcedido() throws AlunoDuplicadoException {
		try {
			service.cadastrar("Jiraya");
			service.cadastrar("Jaspion");
			service.cadastrar("Godzilla");
			service.cadastrar("Satan Goss");
			Assert.assertFalse(service.estaInscrito("Satan Goss"));

			fail("Deveria dar erro");
		} catch (SalaLotadaException cause) {
			logger.info(cause.getMessage());
		}
	}

}
