package rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import domain.Comment;
import domain.services.CommentService;

public class CommentResources {
	private CommentService db = new CommentService();
	
	//usuwanie komentarzy
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
	Comment result = db.get(id);
	if(result==null)
		return Response.status(404).build();
	db.update(result);
	return Response.ok().build();
	}

}
