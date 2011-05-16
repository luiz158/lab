package br.gov.consegi.inscricao;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class InscricaoTest {

	@Inject
	private Logger logger;

	@Inject
	private InscricaoConfig config;

	@Inject
	private Inscricao inscricao;

	@Before
	public void setUp() {
		for (String aluno : inscricao.getInscritos()) {
			inscricao.descadastrar(aluno);
		}
	}

	@Test
	public void cadastrarAlunoComSucesso() {
		inscricao.cadastrar("Wilson");
		Assert.assertTrue(inscricao.getInscritos().contains("Wilson"));
	}

	@Test
	public void cadastrarAlunoJaInscrito() {
		inscricao.cadastrar("Serge");
		try {
			inscricao.cadastrar("Serge");
			fail();
		} catch (AlunoDuplicadoException cause) {
			logger.info(cause.getMessage());
		}
	}
	
	@Test
	public void cadastrarAlunoComNomeEmBranco() {
		try {
			inscricao.cadastrar("");
			fail();
		} catch (CampoObrigatorioException cause) {
			logger.info(cause.getMessage());
		}
	}

	@Test
	public void cadastrarAlemDoLimiteDaSala() {
		for (int i = 0; i < config.getTamanhoSala(); i++) {
			inscricao.cadastrar("aluno" + i);
		}

		try {
			inscricao.cadastrar("Um aluno a mais");
			fail();
		} catch (SalaLotadaException cause) {
			logger.info(cause.getMessage());
		}
	}
	
	@Test
	public void descadatrarAlunoCadastrado() {
		for (int i = 0; i < config.getTamanhoSala(); i++) {
			inscricao.cadastrar("aluno" + i);
		}
		
		try {
			inscricao.descadastrar(inscricao.getInscritos().get(0));
		} catch (AlunoNaoCadastradoException e) {
			fail();
		}
	}
	
	@Test
	public void descadatrarAlunoNaoCadastrado() {
		for (int i = 0; i < config.getTamanhoSala(); i++) {
			inscricao.cadastrar("aluno" + i);
		}
		
		try {
			inscricao.descadastrar("Aluno nao cadastrado");
			fail();
		} catch (AlunoNaoCadastradoException cause) {
			logger.info(cause.getMessage());
		}
	}

}
