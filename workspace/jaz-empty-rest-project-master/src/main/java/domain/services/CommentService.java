package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Comment;

//klasa do wykonywania prostych operacji na danych w pamiêci aplikacji
public class CommentService {

	private static List<Comment> db = new ArrayList<Comment>();
	private static int currentId = 1;
	public static List<Comment> getAll() {
		return db;
	}
	public Comment get(int id) {
		for (Comment c: db) {
			if(c.getId()==id)
				return c;
		}
		return null;
	}
	
	public void add(Comment c) {
		c.setId(++currentId);
		db.add(c);
	}
	
	public void update(Comment comment) {
		for(Comment c:db) {
			if(c.getId()==comment.getId()) {
				c.setCommentText(comment.getCommentText());
			}
		}
	}
	
	public void delete(Comment c) {
		db.remove(c);
	}
	
	
}
