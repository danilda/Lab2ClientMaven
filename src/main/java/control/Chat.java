package control;

import java.util.ArrayList;

/**
 * Created by User on 05.08.2016.
 */
public interface Chat {
    void refreshMessages(ArrayList<String> parameters);
    boolean isLife();
}
