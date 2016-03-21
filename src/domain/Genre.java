package domain;

public class Genre {
int genreId;
String genreName;

public Genre(int genreId, String genreName) {
	super();
	this.genreId = genreId;
	this.genreName = genreName;
}
public int getGenreId() {
	return genreId;
}
public void setGenreId(int genreId) {
	this.genreId = genreId;
}
public String getGenreName() {
	return genreName;
}
public void setGenreName(String genreName) {
	this.genreName = genreName;
}
public String toString(){
	return new StringBuffer().append("genreId:"+genreId+"genreName: "+genreName).toString(); 
}

}
