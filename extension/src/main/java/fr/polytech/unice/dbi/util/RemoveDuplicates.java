package fr.polytech.unice.dbi.util;

import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a Reporter used to remove duplicates in Patch lists.
 */
public class RemoveDuplicates implements Reporter {

    /**
     * This method takes a LogoList of Patches as an argument and returns new Logolist where duplicates
     * references have been removed.
     *
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {

        LogoList list = args[0].getList();
        LogoListBuilder builder = new LogoListBuilder();

        for (int i = 0; i < list.length(); i++) {
            if (!before(list, i)) {
                builder.add(list.get(i));
            }
        }
        return builder.toLogoList();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ListType()}, Syntax.ListType());
    }

    /**
     * This method returns true if the current Patch is found in the previous elements of the list.
     *
     * @param list
     * @param index
     * @return
     */
    private boolean before(LogoList list, int index) {
        Patch patch = (Patch) list.get(index);
        long id = patch.id();
        for (int i = index - 1; i > 0; i--) {
            long secId = ((Patch) list.get(i)).id();
            if (id == secId) {
                return true;
            }
        }
        return false;
    }
}
