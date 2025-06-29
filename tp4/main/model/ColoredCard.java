package org.udesa.unoback.model;

import java.util.Objects;
import java.util.Set;

public abstract class ColoredCard extends Card {
    protected Set<String> existingColors = Set.of("Red", "Blue", "Green", "Yellow");
    protected String color = "";

    public ColoredCard( String aColor ) {
        if (!existingColors.contains(aColor)){
            throw new IllegalArgumentException("El color " + aColor + " no es valido");
        }
        color = aColor;
    }
    public boolean acceptsOnTop( Card aCard ) { return  aCard.yourColorIs( color() );   }
    public boolean yourColorIs( String aColor ) { return color.equals( aColor );  }
    public String color() { return color;  }

    public boolean equals( Object o ) { return super.equals( o ) && color.equals( ColoredCard.class.cast( o ).color );  }
    public int hashCode() {             return Objects.hash( color );}

    public JsonCard asJson() { return new JsonCard( color, null, getClass().getSimpleName(), unoShouted() ); }
}
