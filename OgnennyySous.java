public class OgnennyySous extends Dekorator {
    public OgnennyySous(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return getBlyudo().getNazvaniye() + " + Огненный соус";
    }

    @Override
    public int getCena() {
        return getBlyudo().getCena() + 40;
    }
}
