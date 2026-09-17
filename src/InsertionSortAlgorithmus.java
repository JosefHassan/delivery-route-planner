import java.util.ArrayList;

public class InsertionSortAlgorithmus {

    public void direkteZieleNachEntfernungSortieren(Graph graph, String startName) {

        int startIndex = graph.findeOrtIndex(startName);

        if (startIndex == -1) {
            System.out.println("Fehler: Start-Ort wurde nicht gefunden.");
            return;
        }

        ArrayList<Verbindung> verbindungen = graph.getVerbindungenVonOrt(startIndex);

        if (verbindungen.size() == 0) {
            System.out.println("Von " + startName + " gibt es keine direkten Ziele.");
            return;
        }


        String[] zielNamen = new String[verbindungen.size()];
        int[] entfernungen = new int[verbindungen.size()];

        for (int i = 0; i < verbindungen.size(); i++) {

            Verbindung verbindung = verbindungen.get(i);

            zielNamen[i] = verbindung.getZiel().getName();
            entfernungen[i] = verbindung.getEntfernung();
        }


        for (int i = 1; i < entfernungen.length; i++) {

            String aktuellerName = zielNamen[i];

            int aktuelleEntfernung = entfernungen[i];

            int j = i - 1;

            while (j >= 0 && entfernungen[j] > aktuelleEntfernung) {

                entfernungen[j + 1] = entfernungen[j];
                zielNamen[j + 1] = zielNamen[j];

                j--;
            }

            entfernungen[j + 1] = aktuelleEntfernung;
            zielNamen[j + 1] = aktuellerName;
        }


        System.out.println("Direkte Ziele ab " + startName + " sortiert nach Entfernung:");

        for (int i = 0; i < entfernungen.length; i++) {
            System.out.println("- " + zielNamen[i] + " (" + entfernungen[i] + " km)");
        }
    }
}
