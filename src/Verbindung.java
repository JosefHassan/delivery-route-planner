public class Verbindung {

    private Ort ziel;

    private int entfernung;


    public Verbindung(Ort ziel, int entfernung) {
        this.ziel = ziel;
        this.entfernung = entfernung;
    }

    public Ort getZiel() {
        return ziel;
    }

    public int getEntfernung() {
        return entfernung;
    }
}
