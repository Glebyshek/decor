public class DvoynayaPorciyaOleninyi extends Dekorator {
    public DvoynayaPorciyaOleninyi(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return getBlyudo().getNazvaniye() + " + Двойная порция оленины";
    }

    @Override
    public int getCena() {
        return getBlyudo().getCena() + 20;
    }
}
