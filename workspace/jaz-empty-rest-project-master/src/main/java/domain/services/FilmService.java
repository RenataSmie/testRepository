package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Film;

//klasa do wykonywania prostych operacji na danych w pamiêci aplikacji
public class FilmService {
	
	private static List<Film> db = new ArrayList<Film>();
	private static int currentId = 1;
	public static List<Film> getAll() {
		return db;
	}
	public Film get(int id) {
		for (Film f: db) {
			if(f.getId()==id)
				return f;
		}
		return null;
	}
	
	public void add(Film f) {
		f.setId(++currentId);
		db.add(f);
	}
	
	public void update(Film film) {
		for(Film f:db) {
			if(f.getId()==film.getId()) {
				f.setName(film.getName());
				f.setProductionYear(film.getProductionYear());
				f.setDirector(film.getDirector());
			}
		}
	}
	
	public void delete(Film f) {
		db.remove(f);
	}
}
