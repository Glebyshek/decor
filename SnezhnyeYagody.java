public class SnezhnyeYagody extends Dekorator {
    public SnezhnyeYagody(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return getBlyudo().getNazvaniye() + " + Снежные ягоды";
    }

    @Override
    public int getCena() {
        return getBlyudo().getCena() + 5;
    }
}
