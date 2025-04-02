package anillo;

public class Ring {
    private Object cargo;
    private Ring nextRing;
    private Ring prevRing;


    public Ring next() {
        if ( nextRing == null ) {
            throw new RuntimeException("Ring is empty");
        }
        return nextRing;
    }

    public Object current() {
        if ( nextRing == null ) {
            throw new RuntimeException("Ring is empty");
        }
        return cargo;
    }

    public Ring add( Object cargo ) {
        if ( nextRing == null ) {
            this.cargo = cargo;
            this.nextRing = this;
            this.prevRing = this;
        }
        else {
            Ring newRing = new Ring();
            newRing.cargo = cargo;
            newRing.nextRing = this;
            this.prevRing.nextRing = newRing;
            newRing.prevRing = this.prevRing;
            this.prevRing = newRing;
            return newRing;
        }
        return this;
    }

    public Ring remove() {
        if ( nextRing == null ) {
            throw new RuntimeException("Ring is empty");
        } else if (nextRing == this) {
            nextRing = null;
            prevRing = null;
            cargo = null;
        } else {
            prevRing.nextRing = this.nextRing;
            nextRing.prevRing = this.prevRing;

        }
        return nextRing;
    }
}
