public class SnezhnyeYagody extends Dekorator {

    public SnezhnyeYagody(Blyudo blyudo) {
        super(blyudo);
    }
    @Override
    protected String getDobavkaNazvaniye() {
        return " + Снежные ягоды";
    }
    @Override
    protected int getDobavkaCena() {
        return 5;
    }
}
