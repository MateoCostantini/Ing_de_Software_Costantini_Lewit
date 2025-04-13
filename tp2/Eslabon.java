package anillo;

public abstract class Eslabon {
    protected Object cargo;
    protected Eslabon nextEslabon;
    protected Eslabon prevEslabon;

    public abstract Eslabon next();
    public abstract Object current();
    public abstract Eslabon add(Object cargo);
    public abstract Eslabon remove(Eslabon actualEslabon);
    public abstract Eslabon redefineActualEslabon(Eslabon actualEslabon);
}

class NullEslabon extends Eslabon {
    public NullEslabon next() {
        throw new RuntimeException("Ring is empty");
    }

    public Object current() {
        throw new RuntimeException("Ring is empty");

    }

    public Eslabon add(Object cargo) {
        ExistsEslabon newEslabon = new ExistsEslabon(cargo);

        newEslabon.nextEslabon = newEslabon;
        newEslabon.prevEslabon = newEslabon;
        return newEslabon;
    }

    public Eslabon remove(Eslabon actualEslabon) {
        throw new RuntimeException("Ring is empty");
    }
    public Eslabon redefineActualEslabon(Eslabon actualEslabon){
        return this;
    }

}


class ExistsEslabon extends Eslabon{
    public ExistsEslabon(Object cargo){
        this.cargo = cargo;
    }
    public Eslabon next() {
        return nextEslabon;
    }


    public Object current() {
        return cargo;
    }

    public ExistsEslabon add( Object cargo ) {
        ExistsEslabon newEslabon = new ExistsEslabon(cargo);
        newEslabon.nextEslabon = this;
        this.prevEslabon.nextEslabon = newEslabon;
        newEslabon.prevEslabon = this.prevEslabon;
        this.prevEslabon = newEslabon;
        return newEslabon;
    }
    public Eslabon remove(Eslabon actualEslabon){
        actualEslabon.prevEslabon.nextEslabon = actualEslabon.nextEslabon;
        actualEslabon.nextEslabon.prevEslabon = actualEslabon.prevEslabon;
        return actualEslabon.nextEslabon;
    }
    public Eslabon redefineActualEslabon(Eslabon actualEslabon){
        return actualEslabon;
    }

}
