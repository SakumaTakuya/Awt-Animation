package animation;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;

public class AbsScaleAnimation extends Animation{
    private int fromWidth;
    private int fromHeight;
    private int toWidth;
    private int toHeight;

    public AbsScaleAnimation(int fromWidth, int fromHeight, int toWidth, int toHeight, double length){
        super(length);
        this.fromWidth = fromWidth;
        this.fromHeight = fromHeight;
        this.toWidth = toWidth;
        this.toHeight = toHeight;
    }

    public void animate(Component component, double rate){
        int w = (int) ((1 - rate) * fromWidth + rate * toWidth);
        int h = (int) ((1 - rate) * fromHeight + rate * toHeight);
        Rectangle r = component.getBounds();

        component.setBounds(r.x, r.y, w, h);
    }

    @Override
    public String toString() {
        return "AbsScaleAnimation:(" + fromWidth + "," + fromHeight + ")->(" + toWidth + "," + toHeight + ")";  
    }
}