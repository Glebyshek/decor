public class OgnennyySous extends Dekorator {

    public OgnennyySous(Blyudo blyudo) {
        super(blyudo);
    }
    @Override
    protected String getDobavkaNazvaniye() {
        return " + Огненный соус";
    }
    @Override
    protected int getDobavkaCena() {
        return 40;
    }
}
