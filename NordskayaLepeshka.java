public class NordskayaLepeshka extends Dekorator {
    public NordskayaLepeshka(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return getBlyudo().getNazvaniye() + " + Нордская лепешка";
    }

    @Override
    public int getCena() {
        return getBlyudo().getCena() + 7;
    }
}
