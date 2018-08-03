package animation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

abstract class ActionInvoker {
    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
    private String command;
    
    public void addActionListener(ActionListener e){
        this.actionListeners.add(e);
    }

    public void removeActionListener(ActionListener e){
        this.actionListeners.remove(e);
    }

    public void setCommand(String command){
        this.command = command;
    }

    public String getCommand(){
        return this.command;
    }

    protected void invoke(){
        this.invoke(0);
    }

    protected void invoke(int id){
        for(ActionListener e : actionListeners) e.actionPerformed(new ActionEvent(this, id, command));
    }
}