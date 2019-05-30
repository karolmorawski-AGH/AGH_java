import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	private String id;
	private String title;

	public Movie(String id, String title) throws JSONException {
		this.id = id;
		this.title = title;
	}

	public Movie() throws JSONException {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//JSON PARSE
	public ArrayList<Movie> getMovieFromJSON(String jsonstring) throws JSONException {
		JSONArray jsonArr = new JSONArray("["+jsonstring+"]");
		List<Movie> dataList = new ArrayList<Movie>();
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			Movie data = new Movie();
			data.setId(jsonObj.getString("id"));
			data.setTitle(jsonObj.getString("title"));
			dataList.add(data);
		}

		return (ArrayList<Movie>) dataList;
	}


}
