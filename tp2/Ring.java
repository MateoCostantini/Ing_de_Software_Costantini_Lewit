package anillo;
import java.util.Stack;


public class Ring {
    private Eslabon actualEslabon;
    protected Stack<Eslabon> stack;

    public Ring(){
        actualEslabon = new NullEslabon();
        stack = new Stack<>();
        stack.push(actualEslabon);
    }

    public Ring next() {
        actualEslabon = actualEslabon.next();
        return this;
    }

    public Object current() {
        return actualEslabon.current();
    }

    public Ring add( Object cargo ) {
        actualEslabon = actualEslabon.add(cargo);
        stack.push(actualEslabon);
        return this;
    }

    public Ring remove() {
        actualEslabon = stack.pop().remove(actualEslabon);
        actualEslabon = stack.peek().redefineActualEslabon(actualEslabon);
        return this;
    }
}

