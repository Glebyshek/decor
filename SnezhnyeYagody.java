public class SnezhnyeYagody extends Dekorator {

    public SnezhnyeYagody(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return blyudo.getNazvaniye() + " + Снежные ягоды";
    }

    @Override
    public int getCena() {
        return blyudo.getCena() + 5;
    }
}
