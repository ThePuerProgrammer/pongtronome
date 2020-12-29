package model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Component {
    
    protected int x;
    protected int y;
    protected int z;
    protected Color c;
    protected int w;
    protected int h;

    public Component() {
        this(0, 0);
    }

    public Component(int x, int y) {
        this(x, y, 0, 0, Color.RED);
    } 

    public Component(int x, int y, int w, int h, Color c) {
        this(x, y, 0, w, h, c);
    }

    public Component(int x, int y, int z, int w, int h, Color c) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.c = c;
    }

    public abstract void render(Graphics2D g2);
    public abstract void animate();
}
