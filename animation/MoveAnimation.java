package animation;
import java.awt.Rectangle;
import java.awt.Component;

public class MoveAnimation extends Animation{
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public MoveAnimation(int fromX, int fromY, int toX, int toY, double length){
        super(length);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void animate(Component component, double rate){
        //System.out.println(rate);
        int posX = (int) ((1 - rate) * fromX + rate * toX);
        int posY = (int) ((1 - rate) * fromY + rate * toY);
        //component.setLocation(posX, posY);
        Rectangle r = component.getBounds();
        component.setBounds(posX, posY, r.width, r.height);
    }

    @Override
    public String toString() {
        return "MoveAnimation:(" + fromX + "," + fromY + ")->(" + toX + "," + toY + ")";  
    }
}