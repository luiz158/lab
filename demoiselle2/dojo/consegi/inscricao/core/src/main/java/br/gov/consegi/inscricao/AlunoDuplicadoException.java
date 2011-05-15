package br.gov.consegi.inscricao;

import static br.gov.frameworkdemoiselle.message.SeverityType.ERROR;
import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(severity = ERROR)
public class AlunoDuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;

}
