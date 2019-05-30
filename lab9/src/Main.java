import org.json.JSONException;

import java.util.concurrent.CountDownLatch;

public class Main {

    private static final String BASE_URL = "https://java.kisim.eu.org/actors/search/";
    public static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws JSONException {

        Actor actor1=new Actor("John Travolta");
        Actor actor2=new Actor("Benedict Cumberbatch");

        ActorSearch as1=new ActorSearch(actor1);
        ActorSearch as2=new ActorSearch(actor2);

        as1.obtainID();
       //as2.obtainID();

        actor1=as1.getX();
       // actor2=as2.getX();

        Movie temp = new Movie("tt6038600", "Goryle we mgle");
        as1.getActorsOfMovie(temp);

        //as1.checkRelation(actor1, actor2);

        //System.out.println(actor1);
        //System.out.println(actor2);
    }
}
