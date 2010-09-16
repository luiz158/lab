package app.poll;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("serial")
public class Voto implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String ip;

	@NotNull
	@Temporal(TemporalType.TIME)
	private Date hora;

	@NotNull
	private String opcao;

	// Gerado pelo Eclipse
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (opcao == null) {
			if (other.opcao != null)
				return false;
		} else if (!opcao.equals(other.opcao))
			return false;
		return true;
	}

	public Date getHora() {
		return hora;
	}

	public Long getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getOpcao() {
		return opcao;
	}

	// Gerado pelo Eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((opcao == null) ? 0 : opcao.hashCode());
		return result;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

}
