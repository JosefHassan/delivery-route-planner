import java.util.ArrayList;

public class Graph {

    private ArrayList<Ort> orte;

    private ArrayList<ArrayList<Verbindung>> verbindungen;

    public Graph() {

        orte = new ArrayList<>();

        verbindungen = new ArrayList<>();
    }

    public void ortHinzufuegen(String name) {

        if (findeOrtIndex(name) != -1) {
            System.out.println("Ort existiert bereits: " + name);
            return;
        }

        Ort neuerOrt = new Ort(name);

        orte.add(neuerOrt);

        verbindungen.add(new ArrayList<>());
    }

    public void alleOrteAnzeigen() {

        System.out.println("Alle Orte:");

        for (int i = 0; i < orte.size(); i++) {

            Ort aktuellerOrt = orte.get(i);

            System.out.println(i + ": " + aktuellerOrt.getName());
        }
    }

    public int findeOrtIndex(String name) {

        for (int i = 0; i < orte.size(); i++) {

            Ort aktuellerOrt = orte.get(i);


            if (aktuellerOrt.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    public void verbindungHinzufuegen(String startName, String zielName, int entfernung) {

        int startIndex = findeOrtIndex(startName);
        int zielIndex = findeOrtIndex(zielName);

        if (startIndex == -1 || zielIndex == -1) {
            System.out.println("Fehler: Ort wurde nicht gefunden.");
            return;
        }

        Ort zielOrt = orte.get(zielIndex);

        Verbindung neueVerbindung = new Verbindung(zielOrt, entfernung);

        verbindungen.get(startIndex).add(neueVerbindung);
    }

    public void beidseitigeVerbindungHinzufuegen(String ort1, String ort2, int entfernung) {

        verbindungHinzufuegen(ort1, ort2, entfernung);

        verbindungHinzufuegen(ort2, ort1, entfernung);
    }

    public void alleVerbindungenAnzeigen() {

        System.out.println("Alle Verbindungen:");

        for (int i = 0; i < orte.size(); i++) {

            Ort startOrt = orte.get(i);

            ArrayList<Verbindung> verbindungenVonDiesemOrt = verbindungen.get(i);

            for (int j = 0; j < verbindungenVonDiesemOrt.size(); j++) {

                Verbindung verbindung = verbindungenVonDiesemOrt.get(j);

                System.out.println(
                        startOrt.getName()
                                + " -> "
                                + verbindung.getZiel().getName()
                                + " ("
                                + verbindung.getEntfernung()
                                + " km)"
                );
            }
        }
    }

    public int getAnzahlOrte() {
        return orte.size();
    }

    public Ort getOrt(int index) {
        return orte.get(index);
    }

    public ArrayList<Verbindung> getVerbindungenVonOrt(int index) {
        return verbindungen.get(index);
    }
}
