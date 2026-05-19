public abstract class Dekorator implements Blyudo {
    protected abstract String getDobavkaNazvaniye();
    protected abstract int getDobavkaCena();
    
    private final Blyudo blyudo;

    protected Dekorator(Blyudo blyudo) {
        this.blyudo = blyudo;
    }

    @Override
    public final String getNazvaniye() {
        return blyudo.getNazvaniye() + getDobavkaNazvaniye();
    }

    @Override
    public final int getCena() {
        return blyudo.getCena() + getDobavkaCena();
    }
}
