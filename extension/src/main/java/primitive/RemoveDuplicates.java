package primitive;

import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class RemoveDuplicates implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {

        LogoList list = args[0].getList();
        LogoListBuilder builder = new LogoListBuilder();

        for(int i = 0; i < list.length(); i ++) {
            if (!before(list, i)){
                builder.add(list.get(i));
            }
        }
        return builder.toLogoList();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ListType()}, Syntax.ListType());
    }

    public boolean before(LogoList list, int index) {
        Patch patch = (Patch) list.get(index);
        long id = patch.id();
        for(int i = index - 1; i > 0; i--) {
            long secId = ((Patch)list.get(i)).id();
            if(id == secId) {
                return true;
            }
        }
        return false;
    }
}
