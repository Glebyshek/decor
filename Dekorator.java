public abstract class Dekorator implements Blyudo {
    private final Blyudo blyudo;
    
    public Dekorator(Blyudo blyudo) {
        this.blyudo = blyudo;
    }
    
    public Blyudo getBlyudo() {
        return blyudo;
    }
}
