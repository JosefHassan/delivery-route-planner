import java.util.ArrayList;

public class TiefensucheAlgorithmus {

    public void erreichbareOrteAnzeigen(Graph graph, String startName) {

        int startIndex = graph.findeOrtIndex(startName);

        if (startIndex == -1) {
            System.out.println("Fehler: Start-Ort wurde nicht gefunden.");
            return;
        }

        boolean[] besucht = new boolean[graph.getAnzahlOrte()];

        System.out.println("Erreichbare Orte ab " + startName + ":");

        besucheOrt(graph, startIndex, besucht);
    }

    private void besucheOrt(Graph graph, int aktuellerIndex, boolean[] besucht) {

        besucht[aktuellerIndex] = true;

        Ort aktuellerOrt = graph.getOrt(aktuellerIndex);

        System.out.println("- " + aktuellerOrt.getName());

        ArrayList<Verbindung> verbindungen = graph.getVerbindungenVonOrt(aktuellerIndex);

        for (int i = 0; i < verbindungen.size(); i++) {

            Verbindung verbindung = verbindungen.get(i);

            Ort zielOrt = verbindung.getZiel();

            int zielIndex = graph.findeOrtIndex(zielOrt.getName());

            if (!besucht[zielIndex]) {
                besucheOrt(graph, zielIndex, besucht);
            }
        }
    }
}
