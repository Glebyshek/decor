public class NordskayaLepeshka extends Dekorator {

    public NordskayaLepeshka(Blyudo blyudo) {
        super(blyudo);
    }
    @Override
    protected String getDobavkaNazvaniye() {
        return " + Нордская лепешка";
    }
    @Override
    protected int getDobavkaCena() {
        return 7;
    }
}
