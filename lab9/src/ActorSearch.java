import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ActorSearch  {
    String name;
    Actor x;

    ActorSearch(Actor x){
        this.name=x.getName();
        this.x=x;
    }

    Actor getX() {
        return x;
    }

    void obtainID() {
        System.out.println("Obtaining id for actor: " + x.getName());

        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Response response=null;
        String result="";
        String BASE_URL = "https://java.kisim.eu.org/actors/search/";

        Request request = new Request.Builder()
                .url(BASE_URL + name)
                .build();

        Call call = client.newCall(request);
        try {
            response = call.execute();
            result=response.body().string();
            result=result.substring(1,result.length()-1);
            x=new ObjectMapper().readValue(result, Actor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Obtained id for " + x.getName());

    }


    public boolean doesActorExist(String movieID, String x){
        okhttp3.OkHttpClient client=new okhttp3.OkHttpClient();
        Response response=null;
        String result="";
        String aktorzy="https://java.kisim.eu.org/actors/movies/";
        Request request=new Request.Builder().url(aktorzy+movieID).build();
        Call call2=client.newCall(request);
        try{
            response=call2.execute();
            result=response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }
        if(result.contains(x))
            return true;
        else
            return false;
    }


    public void checkRelation(Actor ac1, Actor ac2){
        //Getting movie list
        okhttp3.OkHttpClient client=new okhttp3.OkHttpClient();
        Response response=null;
        String result="",movieID="";
        String filmy="https://java.kisim.eu.org/actors/"+ac1.getId()+"/movies";


        //Checking movies
        boolean found=false;

        Request request=new Request.Builder()
                .url(filmy)
                .build();
        Call call=client.newCall(request);
        try{
            response=call.execute();
            result=response.body().string();
            result=result.substring(1,result.length()-1);
        }catch(IOException e){
            e.printStackTrace();
        }
        String[]moviesArray=result.split("}");

        //sprawdzamy filmy
        for(int i=0;i<moviesArray.length;i++){
            if(i==0)
                movieID=moviesArray[i].substring(8,16);
            else
                movieID=moviesArray[i].substring(9,17);

            found= doesActorExist(movieID,ac2.getId());
            if(found==true){
                System.out.println("ZNALAZLEM MOVIE ID:"+moviesArray[i]);
                break;
            }
        }

        for(int i=1;i<6;i++){
        }
    }


    public ArrayList<Actor> getActorsOfMovie(Movie movie) throws JSONException {

        //Getting actor list
        okhttp3.OkHttpClient client=new okhttp3.OkHttpClient();
        Response response=null;
        String result="",movieID="";
        String filmy="https://java.kisim.eu.org/movies/"+ movie.getId();

        Request request=new Request.Builder()
                .url(filmy)
                .build();
        Call call=client.newCall(request);
        try{
            response=call.execute();
            result=response.body().string();
            result=result.substring(1,result.length()-1);
        }catch(IOException e){
            e.printStackTrace();
        }

        Actor temp = new Actor("Janusz", "xD");
        ArrayList<Actor> returnedList = new ArrayList<Actor>();
        returnedList.addAll(temp.getActorFromJSON(result));

        return returnedList;
    }


    public ArrayList<Movie> getMoviesOfActor(Actor actor) throws JSONException {

        //Getting movie list
        okhttp3.OkHttpClient client=new okhttp3.OkHttpClient();
        Response response=null;
        String result="",movieID="";
        String movies="https://java.kisim.eu.org/actors/"+actor.getId()+"/movies";

        Request request=new Request.Builder()
                .url(movies)
                .build();
        Call call=client.newCall(request);
        try{
            response=call.execute();
            result=response.body().string();
            result=result.substring(1,result.length()-1);
        }catch(IOException e){
            e.printStackTrace();
        }

        Movie temp = new Movie();
        ArrayList<Movie> returnedList = new ArrayList<Movie>();
        returnedList.addAll(temp.getMovieFromJSON(result));

        return returnedList;
    }

}
