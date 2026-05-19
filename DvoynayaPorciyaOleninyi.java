public class DvoynayaPorciyaOleninyi extends Dekorator {

    public DvoynayaPorciyaOleninyi(Blyudo blyudo) {
        super(blyudo);
    }
    @Override
    protected String getDobavkaNazvaniye() {
        return " + Двойная порция оленины";
    }
    @Override
    protected int getDobavkaCena() {
        return 20;
    }
}
