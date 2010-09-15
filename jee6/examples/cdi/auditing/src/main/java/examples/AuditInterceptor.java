package examples;

import java.util.Date;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Audit
@Interceptor
public class AuditInterceptor {
	
	@Inject
	private Logger logger;
	
	@AroundInvoke
	public Object manage(InvocationContext ic) throws Exception {
		logger.info("acessando o método " + ic.getMethod().toGenericString() + " em " + new Date().toString());
		return ic.proceed();
	}
	
}
