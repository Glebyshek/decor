public class NordskayaLepeshka extends Dekorator {
    public NordskayaLepeshka(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return blyudo.getNazvaniye() + " + Нордская лепёшка";
    }

    @Override
    public int getCena() {
        return blyudo.getCena() + 7;
    }
}
