package lab.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lab.business.BookmarkBC;
import lab.domain.Bookmark;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@Path("bookmark")
public class BookmarkREST {

	@Inject
	private BookmarkBC bc;

	@GET
	@Produces("application/json")
	public List<Bookmark> findAll() {
		List<Bookmark> result = bc.findAll();
		return result.isEmpty() ? null : result;
	}

	@GET
	@Produces("application/json")
	@Path("{id}")
	public Response load(@PathParam("id") Long id) {
		Bookmark result = bc.load(id);
		Status status = (result != null ? OK : NOT_FOUND);
		return Response.status(status).entity(result).build();
	}

	@DELETE
	@Consumes("application/json")
	@Transactional
	public void delete(List<Long> ids) {
		bc.delete(ids);
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void delete(@PathParam("id") Long id) {
		bc.delete(id);
	}

	@POST
	@Transactional
	@Consumes("application/json")
	public Response insert(Bookmark entity) {
		if (entity.getId() != null) {
			String message = "Não defina o atributo \"id\"";
			return Response.status(BAD_REQUEST).entity(message).build();
		}

		Long id = bc.insert(entity).getId();

		HttpServletRequest request = Beans.getReference(HttpServletRequest.class);
		String requestURL = request.getRequestURL().toString();
		String location = requestURL + "/" + id.toString();

		return Response.status(CREATED).type(TEXT_PLAIN).entity(id).header("Location", location).build();
	}

	// @PUT
	// @Path("{cpf}")
	// @Transactional
	// @Consumes("application/json")
	// public Response insertx(Funcionario entity, @PathParam("cpf") String cpf) {
	// if (entity.getCPF() != null) {
	// String message = "Não defina o atributo \"cpf\"";
	// return Response.status(BAD_REQUEST).entity(message).build();
	// }
	//
	// entity.setCPF(cpf);
	// bc.insert(entity);
	//
	// return Response.status(CREATED).build();
	// }
}
