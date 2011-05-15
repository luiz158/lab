package br.gov.consegi.inscricao;

import static br.gov.frameworkdemoiselle.message.SeverityType.ERROR;
import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ApplicationException(severity = ERROR)
public class SalaLotadaException extends Exception {

	private static final long serialVersionUID = 1L;

	private static ResourceBundle bundle = Beans.getReference(ResourceBundle.class);

	public SalaLotadaException() {
		super(bundle.getString("sala.lotada"));
	}
}
