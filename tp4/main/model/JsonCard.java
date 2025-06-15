package org.udesa.unoback.model;

import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class JsonCard {
    String color;
    Integer number;
    String type;
    boolean shout;

    public JsonCard() {}
    public JsonCard( String color, Integer number, String type, boolean shout ) {

        this.color = color;
        this.number = number;
        this.type = type;
        this.shout = shout;
    }

    public String toString() {
        return "{ " +
               "\"color\": \"" + color + "\", " +
               "\"number\": \"" + number + "\", " +
               "\"type\": \"" + type + "\", " +
               "\"shout\": \"" + shout + "\" " +
               "}";
    }

    public Card asCard() {
        try {
            Class<?> clazz = Class.forName("org.udesa.unoback.model." + type);
            return (Card) clazz.getMethod("asCard", getClass())
                    .invoke(getClass(), this);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Tipo de carta inválido: " + type, e);
        }catch (Exception e) {
            if (e.getCause() instanceof RuntimeException cause) {
                throw (RuntimeException) cause;
            }
            throw new RuntimeException(e);
        }
    }
}

