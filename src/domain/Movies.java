package domain;

import java.util.ArrayList;
import java.util.List;

public class Movies {
	public int movieId;
	public String title;
	public String year;
	public String Director;
	public String banner_url;
	public String trailer_url;
	public String Star_First_name;
	public String Star_last_name;
	public String star_dob;
	public String Star_photo_url;
	public String genre_of_movie;
	public int s_id;
	public List<Star> stars;
	public List<Genre> genres;

	public Movies(int movieId, String title, String year, String director,
			String banner_url, String trailer_url) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		Director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		/* store stars and genres here! */
		this.stars = new ArrayList<Star>();
		this.genres = new ArrayList<Genre>();
	}

	/* will be deleted */
	public Movies(int movieId, String title, String year, String director,
			String banner_url, String trailer_url, String star_First_name,
			String star_last_name) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		Director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		Star_First_name = star_First_name;
		Star_last_name = star_last_name;
		this.stars = new ArrayList<Star>();
		this.genres = new ArrayList<Genre>();
	}

	public Movies(int s_id, int movieId, String title, String year,
			String director, String banner_url, String trailer_url,
			String star_First_name, String star_last_name, String star_dob,
			String star_photo_url, String genre_of_movie) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		Director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		Star_First_name = star_First_name;
		Star_last_name = star_last_name;
		this.star_dob = star_dob;
		Star_photo_url = star_photo_url;
		this.genre_of_movie = genre_of_movie;
		this.s_id = s_id;
	}

	public String getStar_First_name() {
		return Star_First_name;
	}

	public void setStar_First_name(String star_First_name) {
		Star_First_name = star_First_name;
	}

	public String getStar_last_name() {
		return Star_last_name;
	}

	public void setStar_last_name(String star_last_name) {
		Star_last_name = star_last_name;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}

	public String getTrailer_url() {
		return trailer_url;
	}

	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}

	public List<Star> getStars() {
		return stars;
	}

	public String getStar_dob() {
		return star_dob;
	}

	public void setStar_dob(String star_dob) {
		this.star_dob = star_dob;
	}

	public String getStar_photo_url() {
		return Star_photo_url;
	}

	public void setStar_photo_url(String star_photo_url) {
		Star_photo_url = star_photo_url;
	}

	public String getGenre_of_movie() {
		return genre_of_movie;
	}

	public void setGenre_of_movie(String genre_of_movie) {
		this.genre_of_movie = genre_of_movie;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String toString() {
		return new StringBuffer().append(
				"starId:" + s_id + "movieId:" + movieId + "title: " + title
						+ "year:" + year + "Director: " + Director
						+ "banner_url:" + banner_url + "trailer_url:"
						+ trailer_url + "Star_First_name:" + Star_First_name
						+ "Star_last_name:" + Star_last_name
						+ "genre_of_movie:" + genre_of_movie).toString();
	}

}
