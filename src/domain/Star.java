package domain;

import java.util.List;

public class Star {
	public int id;
	public String firstName;
	public String lastName;
	public String dob;
	public String photo_url;
   public List<Movies> movies;
	public Star() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.dob = "";
		this.photo_url = "";
	}

	public Star(int f_id, String f_firstName, String f_lastName) {
		this.id = f_id;
		this.firstName = f_firstName;
		this.lastName = f_lastName;
		this.dob = "";
		this.photo_url = "";
	}

	public Star(int f_id, String f_firstName, String f_lastName, String f_dob,
			String photo) {
		this.id = f_id;
		this.firstName = f_firstName;
		this.lastName = f_lastName;
		this.dob = f_dob;
		this.photo_url = photo;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public List<Movies> getMovies() {
		return movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies=(movies);
	}

	@Override
	public String toString() {
		return "Star [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", photo_url=" + photo_url
				+ ", movies=" + movies + "]";
	}
	
}
