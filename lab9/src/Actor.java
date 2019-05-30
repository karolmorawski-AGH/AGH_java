import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize(using = ActorDeserializer.class)
public class Actor {

    private String id;
    private String fullName;



    Actor(String id, String name){
        this.id=id;
        this.fullName=name;
    }

    Actor(String fullName){
        this.id = "";
        this.fullName = fullName;
    }

    Actor(){
        this.id="Not provided";
        this.fullName="Not provided";
    }
    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return fullName;
    }

    void setName(String name) {
        this.fullName = name;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o){
            return true;
        }

        if( ( (Actor) o).id.equals(id) ){
            return true;
        }

        //Add class checking because Intellij was giving warnings
        if (getClass() != o.getClass()){
            return false;
        }

        return false;
    }

    /*
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
*/

    @Override
    public String toString() {
        return "Actor{" +
                "ID='" + id + '\'' +
                ", Name='" + fullName + '\'' +
                '}';
    }


    //JSON PARSE
    public Collection<? extends Actor> getActorFromJSON(String jsonstring) throws JSONException {

        int actorsPos = jsonstring.indexOf("\"actors\":");
       jsonstring = jsonstring.substring(actorsPos+9);  //adding length of "actors":

        JSONArray jsonArr = new JSONArray(  jsonstring );
        List<Actor> dataList = new ArrayList<Actor>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Actor data = new Actor();
            data.setId(jsonObj.getString("id"));
            data.setName(jsonObj.getString("name"));
            dataList.add(data);
        }

        return (ArrayList<Actor>) dataList;
    }

}

