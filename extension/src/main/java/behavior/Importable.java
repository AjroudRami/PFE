package behavior;

import java.io.File;
import java.io.IOException;

public interface Importable {

    Behavior importFrom(File f) throws IOException;
}
