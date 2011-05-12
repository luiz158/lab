package br.gov.consegi.inscricao.exception;

import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class SalaLotadaException extends Exception {

	private static final long serialVersionUID = 1L;

	private static ResourceBundle bundle = Beans.getReference(ResourceBundle.class);

	public SalaLotadaException() {
		super(bundle.getString("sala.lotada"));
	}
}
