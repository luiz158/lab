package examples;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Parameter;

@ViewController
public class MyManagedBean {

	@Inject
	private Parameter<String> param1;

	@Inject
	@Name("param2")
	@RequestScoped
	private Parameter<Long> p2;

	@Inject
	@Name("param3")
	private Parameter<Boolean> p3;

	public Parameter<String> getParam1() {
		return param1;
	}

	public Parameter<Long> getParam2() {
		return p2;
	}

	public Parameter<Boolean> getParam3() {
		return p3;
	}
}
