package app.poll;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2010-09-15T22:13:04.166-0300")
@StaticMetamodel(Voto.class)
public class Voto_ {
	public static volatile SingularAttribute<Voto, Long> id;
	public static volatile SingularAttribute<Voto, String> ip;
	public static volatile SingularAttribute<Voto, Date> hora;
	public static volatile SingularAttribute<Voto, String> opcao;
}
