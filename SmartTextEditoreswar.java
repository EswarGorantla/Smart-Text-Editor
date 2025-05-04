//This Java program implements a simple text editor with undo and redo functionality using stacks

import java.util.Stack;

public class SmartTextEditoreswar {
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private String text = "";

    
    public void editText(String newText) {
        undoStack.push(text); 
        text = newText;      
        redoStack.clear();    
    }

    
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text); 
            text = undoStack.pop(); 
        } else {    
            System.out.println("No actions to undo.");
        }
    }

  
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text); 
            text = redoStack.pop(); 
        } else {
            System.out.println("No actions to redo.");
        }
    }

    public String getText() {
        return text;
    }

    public static void main(String[] args) {
       
        SmartTextEditoreswar editor = new SmartTextEditoreswar();

        editor.editText("Hello");
        System.out.println("Current Text: " + editor.getText());

        editor.editText("Hello World!!!");
        System.out.println("Current Text: " + editor.getText());

        editor.undo();
        System.out.println("After Undo: " + editor.getText());

        editor.redo();
        System.out.println("After Redo: " + editor.getText());
    }
}