package behavior;

import kobdig.agent.Agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BehaviorBuilder {

    public static Behavior build(String filename) throws IOException {
        File file = new File(filename);
        InputStream inputStream = new FileInputStream(file);
        Agent agent = new Agent(inputStream);
        return new DBIBehavior(agent);
    }

    public static void saveBehavior(Behavior behavior, String filename) throws IOException{
        File file = new File(filename);
        Savable savable = (Savable) behavior;
        savable.exportTo(file);
    }
}
