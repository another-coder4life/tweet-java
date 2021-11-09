import twitter4j.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Logic {
    private Twitter twitter;
    private PrintStream consolePrinter;
    List<Status> statusList;

    /**
     * @param console
     * The constructor receives a PrintStream as a parameter to exhibit the returned contents on the main execution class
     * The Twitter object is initialized with a factory and the Array List that stores the status as well
     */
    public Logic(PrintStream console) {
        twitter = TwitterFactory.getSingleton();
        statusList = new ArrayList<Status>();
    }

    /**
     *
     * @param message
     * @throws TwitterException
     * @throws IOException
     *
     * The method receives the desired message to be posted as a parameter and throws a Twitter exception in case twitter messes up
     * and an IO in case the input/output messes up
     */
    public void tweetSomething(String message) throws TwitterException, IOException {
        Status status = twitter.updateStatus(message);
        System.out.println("Status update with success. Actual status is [" + status.getText() + "]");
    }

    /**
     *
     * @param handle
     * @throws TwitterException
     * @throws IOException
     *
     * Fetches the tweets for a specified handle name, which is a parameter in this method.
     */
    public void fetchUserTweets (String handle) throws TwitterException, IOException{
        Paging page = new Paging(1,200);
        int p = 1;
        while (p <= 10){
            page.setPage(p);
            statusList.addAll(twitter.getUserTimeline(handle, page));
            p++;
        }
    }

    /**
     *
     * @param handle
     * @throws TwitterException
     * @throws IOException
     *
     * Fetches and outputs the tweets for the given user handle
     */
    public void queryTweets (String handle) throws TwitterException, IOException{
        statusList.clear();
        fetchUserTweets(handle);
        int counter = statusList.size();

        while (counter > 0){
            counter--;
            System.out.println("Tweet #" + counter + ": " + statusList.get(counter).getText());
        }
    }
}
