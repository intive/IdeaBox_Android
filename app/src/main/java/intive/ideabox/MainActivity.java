package intive.ideabox;

import android.app.Activity;
import android.os.Bundle;

import java.util.Iterator;
import java.util.List;

import intive.ideabox.models.DataProvider;
import intive.ideabox.models.IdeaData;


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view);
        DataProvider provider = DataProvider.getInstance();
//        provider.saveIdea("new idea");
        System.out.println("savedIdea");
        List<IdeaData> ideas = provider.loadIdea();
        System.out.println("loadedIdea");
//        if(ideas!=null)
//        System.out.println(ideas.get(1));

//        for (Iterator<IdeaData> iter = ideas.iterator(); iter.hasNext(); ) {
//            IdeaData element = iter.next();
//            System.out.println(element.ideatext);
//        }

    }

}
