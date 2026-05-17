public class DvoynayaPorciyaOleninyi extends Dekorator {
    public DvoynayaPorciyaOleninyi(Blyudo blyudo) {
        super(blyudo);
    }

    @Override
    public String getNazvaniye() {
        return blyudo.getNazvaniye() + " + Двойная порция оленины";
    }

    @Override
    public int getCena() {
        return blyudo.getCena() + 20;
    }
}
