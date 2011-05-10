package br.gov.consegi.inscricao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.consegi.inscricao.Inscricao;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class InscricaoTest {

	private Inscricao inscricao;

	@Before
	public void prepara() {
		inscricao = Beans.getReference(Inscricao.class);
	}

	@Test
	public void cadastrarComSucesso() {
		inscricao.cadastrar("Wilson");
		Assert.assertTrue(inscricao.estaInscrito("Wilson"));
	}

	@Test
	public void verificaAlunoNaoCadastrado() {
		Assert.assertFalse(inscricao.estaInscrito("Super-man"));
	}

	@Test
	public void naoCadastraDuplicado() {
		inscricao.cadastrar("Serge");
		inscricao.cadastrar("Serge");
		Assert.assertEquals(1, inscricao.getQtdInscritos());
	}

	@Test
	public void cadastrarComLimiteExcedido() {
		inscricao.cadastrar("Jiraya");
		inscricao.cadastrar("Jaspion");
		inscricao.cadastrar("Godzilla");
		inscricao.cadastrar("Satan Goss");
		Assert.assertFalse(inscricao.estaInscrito("Satan Goss"));
	}

}
