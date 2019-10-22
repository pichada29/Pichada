package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		List<Movie> list = new ArrayList<Movie>();
		try {
			MoviesResponse allData = movieDataService.fetchAll();
			for (MovieData i : allData){
				String sptext[] = i.getTitle().split(" ");
				for (String text : sptext){
					if (text.equalsIgnoreCase(queryText)){
						Movie movie = new Movie();
						movie.setName(i.getTitle());
						movie.setActors(i.getCast());
						list.add(movie);
					}
				}

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
