public class OgnennyySous extends Dekorator {
    public OgnennyySous(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return blyudo.getNazvaniye() + " + Огненный соус";
    }

    @Override
    public int getCena() {
        return blyudo.getCena() + 10;
    }
}
