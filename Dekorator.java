public class Dekorator implements Blyudo {
    protected Blyudo blyudo;

    public Dekorator(Blyudo blyudo) {
        this.blyudo = blyudo;
    }
    @Override
    public String getNazvaniye() {
        return blyudo.getNazvaniye();
    }

    @Override
    public int getCena() {
        return blyudo.getCena();
    }
}
