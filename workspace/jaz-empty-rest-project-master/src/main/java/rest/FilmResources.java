package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Comment;
import domain.Film;
import domain.services.FilmService;
import domain.services.CommentService;

//klasa reprezentuj¹ca zasoby filmów
@Path("/films")
public class FilmResources {
	
	private FilmService db= new FilmService();
	
	//Pobranie wszystkich filmów w formacie JSON przy u¿yciu metody GET
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAll()
	{
		return db.getAll();
	}
	
	//Zapisanie nowego filmu
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Film film) {
		db.add(film);
		return Response.ok(film.getId()).build();
	}
	
	//Pobranie filmu o podanym id. Je¿eli takiego filmu nie znajdzie, wyrzucamy b³¹d 404
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id")int id) {
		Film result = db.get(id);
		if (result==null) {
			return Response.status(404).build();
			}
		return Response.ok(result).build();
	}
	
	//Zmiana aktualnych danych
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Film f) {
		Film result = db.get(id);
		if(result==null)
			return Response.status(404).build();
		f.setId(id);
		db.update(f);
		return Response.ok().build();
	}
	
	//Wyœwietlenie listy komentarzy danego filmu
	@GET
	@Path("/{filmId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("filmId") int filmId){
		Film result = db.get(filmId);
		if(result==null)
			return null;
		if (result.getComments()==null)
			result.setComments(new ArrayList<Comment>());
		return result.getComments();
	}
	
	//Dodawanie komentarzy do konkretnego filmu
	@POST
	@Path("/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("id") int filmId, Comment comment) {
		Film result = db.get(filmId);
		if(result==null)
			return Response.status(404).build();
		if (result.getComments()==null)
			result.setComments(new ArrayList<Comment>());
		result.getComments().add(comment);
		return Response.ok().build();
	}
	
	//usuwanie filmów
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
	Film result = db.get(id);
	if(result==null)
		return Response.status(404).build();
	db.update(result);
	return Response.ok().build();
	}
}
