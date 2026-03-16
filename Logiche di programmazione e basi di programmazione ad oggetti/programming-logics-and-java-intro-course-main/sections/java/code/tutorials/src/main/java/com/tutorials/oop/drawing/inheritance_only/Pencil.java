package com.tutorials.oop.drawing.inheritance_only;

import com.tutorials.oop.drawing.Curve;

import java.awt.*;

public class Pencil extends DrawingTool {
    public Pencil(Color color) {
        super(color);
    }

    @Override
    public String draw(Curve curve) {
        return "i'm a pencil drawing " + curve.draw();
    }
}
