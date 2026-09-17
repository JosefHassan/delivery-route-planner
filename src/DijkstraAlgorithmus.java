import java.util.ArrayList;

public class DijkstraAlgorithmus {

    private static final int UNENDLICH = 999999;

    public void kuerzesteEntfernungBerechnen(Graph graph, String startName, String zielName) {

        int startIndex = graph.findeOrtIndex(startName);
        int zielIndex = graph.findeOrtIndex(zielName);

        if (startIndex == -1 || zielIndex == -1) {
            System.out.println("Fehler: Start oder Ziel wurde nicht gefunden.");
            return;
        }

        int anzahlOrte = graph.getAnzahlOrte();

        int[] distanzen = new int[anzahlOrte];
        boolean[] besucht = new boolean[anzahlOrte];

        int[] vorgaenger = new int[anzahlOrte];

        for (int i = 0; i < anzahlOrte; i++) {
            distanzen[i] = UNENDLICH;
            besucht[i] = false;

            vorgaenger[i] = -1;
        }

        distanzen[startIndex] = 0;

        for (int i = 0; i < anzahlOrte; i++) {

            int aktuellerIndex = findeKleinsteUnbesuchteDistanz(distanzen, besucht);

            if (aktuellerIndex == -1) {
                break;
            }

            besucht[aktuellerIndex] = true;

            ArrayList<Verbindung> verbindungen = graph.getVerbindungenVonOrt(aktuellerIndex);

            for (int j = 0; j < verbindungen.size(); j++) {

                Verbindung verbindung = verbindungen.get(j);

                Ort nachbarOrt = verbindung.getZiel();

                int nachbarIndex = graph.findeOrtIndex(nachbarOrt.getName());

                int neueDistanz = distanzen[aktuellerIndex] + verbindung.getEntfernung();

                if (neueDistanz < distanzen[nachbarIndex]) {

                    distanzen[nachbarIndex] = neueDistanz;

                    vorgaenger[nachbarIndex] = aktuellerIndex;
                }
            }
        }

        if (distanzen[zielIndex] == UNENDLICH) {
            System.out.println("Es gibt keinen Weg von " + startName + " nach " + zielName + ".");
        } else {
            System.out.println(
                    "Kürzeste Entfernung von "
                            + startName
                            + " nach "
                            + zielName
                            + ": "
                            + distanzen[zielIndex]
                            + " km"
            );

            wegAusgeben(graph, startIndex, zielIndex, vorgaenger);
        }
    }

    private int findeKleinsteUnbesuchteDistanz(int[] distanzen, boolean[] besucht) {

        int kleinsteDistanz = UNENDLICH;
        int kleinsterIndex = -1;

        for (int i = 0; i < distanzen.length; i++) {

            if (!besucht[i]) {

                if (distanzen[i] < kleinsteDistanz) {
                    kleinsteDistanz = distanzen[i];
                    kleinsterIndex = i;
                }
            }
        }

        return kleinsterIndex;
    }

    private void wegAusgeben(Graph graph, int startIndex, int zielIndex, int[] vorgaenger) {

        String weg = graph.getOrt(zielIndex).getName();

        int aktuellerIndex = zielIndex;

        while (aktuellerIndex != startIndex) {

            int vorherigerIndex = vorgaenger[aktuellerIndex];

            if (vorherigerIndex == -1) {
                System.out.println("Weg konnte nicht vollständig angezeigt werden.");
                return;
            }

            weg = graph.getOrt(vorherigerIndex).getName() + " -> " + weg;

            aktuellerIndex = vorherigerIndex;
        }

        System.out.println("Weg: " + weg);
    }
}
