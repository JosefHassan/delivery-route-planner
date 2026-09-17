import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Graph graph = new Graph();

        graph.ortHinzufuegen("Lager");
        graph.ortHinzufuegen("Zentrum");
        graph.ortHinzufuegen("KundeA");
        graph.ortHinzufuegen("KundeB");
        graph.ortHinzufuegen("KundeC");
        graph.ortHinzufuegen("Poststation");
        graph.ortHinzufuegen("Bahnhof");

        graph.beidseitigeVerbindungHinzufuegen("Lager", "Zentrum", 3);
        graph.beidseitigeVerbindungHinzufuegen("Lager", "Poststation", 5);
        graph.beidseitigeVerbindungHinzufuegen("Zentrum", "KundeA", 4);
        graph.beidseitigeVerbindungHinzufuegen("Zentrum", "KundeB", 6);
        graph.beidseitigeVerbindungHinzufuegen("Zentrum", "Bahnhof", 2);
        graph.beidseitigeVerbindungHinzufuegen("Poststation", "KundeC", 3);
        graph.beidseitigeVerbindungHinzufuegen("Bahnhof", "KundeB", 3);
        graph.beidseitigeVerbindungHinzufuegen("Bahnhof", "KundeC", 7);
        graph.beidseitigeVerbindungHinzufuegen("KundeA", "KundeC", 8);

        DijkstraAlgorithmus dijkstra = new DijkstraAlgorithmus();
        TiefensucheAlgorithmus tiefensuche = new TiefensucheAlgorithmus();
        InsertionSortAlgorithmus insertionSort = new InsertionSortAlgorithmus();

        boolean programmLaeuft = true;

        while (programmLaeuft) {

            System.out.println();
            System.out.println("===== LieferRoutenPlaner =====");
            System.out.println("1 - Alle Orte anzeigen");
            System.out.println("2 - Alle Verbindungen anzeigen");
            System.out.println("3 - Kürzesten Weg berechnen");
            System.out.println("4 - Erreichbare Orte anzeigen");
            System.out.println("5 - Direkte Ziele nach Entfernung sortieren");
            System.out.println("6 - Neuen Ort hinzufügen");
            System.out.println("7 - Neue beidseitige Verbindung hinzufügen");
            System.out.println("0 - Programm beenden");
            System.out.print("Auswahl: ");

            String auswahl = scanner.nextLine();

            if (auswahl.equals("1")) {

                graph.alleOrteAnzeigen();

            } else if (auswahl.equals("2")) {

                graph.alleVerbindungenAnzeigen();

            } else if (auswahl.equals("3")) {

                System.out.print("Start-Ort eingeben: ");
                String start = scanner.nextLine();

                System.out.print("Ziel-Ort eingeben: ");
                String ziel = scanner.nextLine();

                dijkstra.kuerzesteEntfernungBerechnen(graph, start, ziel);

            } else if (auswahl.equals("4")) {

                System.out.print("Start-Ort eingeben: ");
                String start = scanner.nextLine();

                tiefensuche.erreichbareOrteAnzeigen(graph, start);

            } else if (auswahl.equals("5")) {

                System.out.print("Start-Ort eingeben: ");
                String start = scanner.nextLine();

                insertionSort.direkteZieleNachEntfernungSortieren(graph, start);

            } else if (auswahl.equals("6")) {

                System.out.print("Name des neuen Ortes: ");
                String neuerOrt = scanner.nextLine();

                if (graph.findeOrtIndex(neuerOrt) != -1) {

                    System.out.println("Ort existiert bereits: " + neuerOrt);

                } else {

                    graph.ortHinzufuegen(neuerOrt);

                    System.out.println("Ort wurde hinzugefügt: " + neuerOrt);
                }

            } else if (auswahl.equals("7")) {


                System.out.print("Erster Ort: ");
                String ort1 = scanner.nextLine();

                System.out.print("Zweiter Ort: ");
                String ort2 = scanner.nextLine();

                if (graph.findeOrtIndex(ort1) == -1 || graph.findeOrtIndex(ort2) == -1) {

                    System.out.println("Fehler: Einer der Orte wurde nicht gefunden.");

                } else {

                    System.out.print("Entfernung in km: ");
                    String entfernungText = scanner.nextLine();

                    try {
                        int entfernung = Integer.parseInt(entfernungText);

                        if (entfernung <= 0) {

                            System.out.println("Fehler: Die Entfernung muss größer als 0 sein.");

                        } else {

                            graph.beidseitigeVerbindungHinzufuegen(ort1, ort2, entfernung);

                            System.out.println("Verbindung wurde hinzugefügt: "
                                    + ort1 + " <-> " + ort2 + " (" + entfernung + " km)");
                        }

                    } catch (NumberFormatException e) {

                        System.out.println("Fehler: Bitte eine gültige Zahl eingeben.");
                    }
                }

            } else if (auswahl.equals("0")) {

                programmLaeuft = false;
                System.out.println("Programm wurde beendet.");

            } else {

                System.out.println("Ungültige Auswahl.");
            }
        }

        scanner.close();
    }
}
