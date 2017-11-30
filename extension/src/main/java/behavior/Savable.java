package behavior;

import java.io.File;
import java.io.IOException;

public interface Savable {

    enum Flag{
        OVERWRITE,
        APPEND,
        COPY
    }

    void exportTo(File f) throws IOException;

    void exportTo(File f, Flag[] flags) throws IOException;

}
