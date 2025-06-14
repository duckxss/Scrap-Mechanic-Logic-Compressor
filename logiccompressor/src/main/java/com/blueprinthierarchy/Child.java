package com.blueprinthierarchy;

public class Child {
    public Bound bounds;
    public String color;
    public Controller controller;
    public Position pos;
    public String shapeId;
    public int xaxis;
    public int zaxis;

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setPos(int x, int y, int z) {
        this.pos.setX(x);
        this.pos.setY(y);
        this.pos.setZ(z);
    }

    public void setXaxis(int xaxis) {
        this.xaxis = xaxis;
    }

    public void setZaxis(int zaxis) {
        this.zaxis = zaxis;
    }

    
}
