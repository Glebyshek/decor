public class Dekorator implements Blyudo {
    private Blyudo blyudo;

    public Dekorator(Blyudo blyudo) {
        this.blyudo = blyudo;
    }
    public Blyudo getBlyudo() {
        return blyudo;
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
