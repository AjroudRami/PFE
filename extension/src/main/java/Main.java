import kobdig.agent.Agent;
import kobdig.agent.Fact;
import kobdig.agent.FactSet;
import kobdig.logic.Operator;
import kobdig.logic.PropositionalAtom;
import kobdig.logic.PropositionalFormula;
import kobdig.logic.TruthDegree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Jeremy on 30/11/2017.
 */
public class Main {

    public static void main(String[] args) {
        String pathname = "./extension/src/main/java/gourmet.apl";

        File file = new File(pathname);

        try {
            // Initialiser l'agent à partir d'un fichier APL
            kobdig.agent.Agent agent = new Agent(new FileInputStream(file));

            // Vous pouvez passer des information à l'agent de la manière suivante :
            // 1. construir une formule, par exemple, "ge and not ff",
            PropositionalFormula phi = new PropositionalFormula(
                    Operator.AND,
                    new PropositionalFormula(new PropositionalAtom("ge")),
                    new PropositionalFormula(Operator.NOT, new PropositionalFormula(new PropositionalAtom("ff")))
                    );
            // 2. passer la formule à l'agent avec un certain dégré de confiance,
            //    par exemple 0.7,
            agent.updateBeliefs(new Fact(phi), new TruthDegree(0.7));
            // N.B.: Cela met à jour automatiquement les désirs et les objectifs de l'agent.

            // En tout moment, vous pouvez vérifier à quel dégré l'agent crois
            // en une formule de la manière3 suivante :
            TruthDegree t = agent.believes(new Fact(phi));

            // Vous pouvez vérifier quels sont les objectifs de l'agent comme-ça :
            FactSet g = agent.goals();
            // Puis, vous pouvez itérer sur les objectifs...
            Iterator<Fact> i = g.factIterator();
            while (i.hasNext()) {
                Fact f = i.next();
                // ... et tester à qual dégré (càd, niveau de priorité)
                // un objectif est adopté par l'agent
                t = g.membership(f);
                System.out.println(t.toString());
                // Attention: si jamais l'objectif f contient une formule non-atomique,
                // il faudra l'analyser pour la décomposer en des objectifs atomiques.
                // Cela n'est pas fait dans notre code parce que jusque là on n'en a
                // jamais eu besoin...
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
