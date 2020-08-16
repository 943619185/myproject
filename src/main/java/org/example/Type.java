package org.example;

public class Type {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type ;

    }
}
