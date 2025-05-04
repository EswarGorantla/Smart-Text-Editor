/*This is a simple smart text editor in Java that allows users to 
insert, delete, undo, and redo text changes dynamically. It uses stacks 
to maintain the previous states for efficient undo/redo operations.*/

import java.util.Scanner;  
import java.util.Stack;  

public class SmartTextEditor1 {  
    private StringBuilder text;  
    private Stack<String> undoStack;  
    private Stack<String> redoStack;  

    public SmartTextEditor1() {  
        text = new StringBuilder();  
        undoStack = new Stack<>();  
        redoStack = new Stack<>();  
    }  

    public void insertText(String newText) {                    //inserts new text with out a space(APPEND)
        saveState();  
        text.append(newText);  
        redoStack.clear();  
    }  

    public void deleteLastCharacter() {                       //Backspace
        if (text.length() > 0) {  
            saveState();  
            text.deleteCharAt(text.length() - 1);  
            redoStack.clear();  
        }  
    }  

    public void deleteText(int start, int end) {  
        if (start >= 0 && end <= text.length() && start < end) {  
            saveState();  
            text.delete(start, end);                //kind of a built in function to delete the particular text 
            redoStack.clear();  
        }  
    }  

    public void clearText() {                        //text clear
        if (text.length() > 0) {  
            saveState();  
            text.setLength(0);  
            redoStack.clear();  
        }  
    }  

    public void undo() {                                         //does the work of ctrl+Z
        if (!undoStack.isEmpty()) {  
            redoStack.push(text.toString());  
            text = new StringBuilder(undoStack.pop());  
        } else {  
            System.out.println("No actions to undo.");  
        }  
    }  

    public void redo() {                                               //does the work of ctrl+y
        if (!redoStack.isEmpty()) {  
            undoStack.push(text.toString());  
            text = new StringBuilder(redoStack.pop());  
        } else {  
            System.out.println("No actions to redo.");  
        }  
    }  
                                 
    public String getText() {                                          //display
        return text.toString();  
    }  

    private void saveState() {                                         //saves the previous state 
        undoStack.push(text.toString());  
    }  

    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
        SmartTextEditor1 editor = new SmartTextEditor1();  
        int choice;  

        while (true) {  
            System.out.println("\nCurrent Text: " + editor.getText());  
            System.out.println("1. Insert Text");  
            System.out.println("2. Delete Last Character");  
            System.out.println("3. Delete Range");  
            System.out.println("4. Clear Text");  
            System.out.println("5. Undo");  
            System.out.println("6. Redo");  
            System.out.println("7. Display Text");  
            System.out.println("8. Exit");  
            System.out.print("Choose an option: ");  
            choice = scanner.nextInt();  
            scanner.nextLine();  

            switch (choice) {  
                case 1:  
                    System.out.print("Enter text to insert: ");  
                    String inputText = scanner.nextLine();  
                    editor.insertText(inputText);  
                    break;  
                case 2:  
                    editor.deleteLastCharacter();  
                    break;  
                case 3:  
                    System.out.print("Enter start index: ");  
                    int start = scanner.nextInt();  
                    System.out.print("Enter end index: ");  
                    int end = scanner.nextInt();  
                    editor.deleteText(start, end);  
                    break;  
                case 4:  
                    editor.clearText();  
                    break;  
                case 5:  
                    editor.undo();  
                    break;  
                case 6:  
                    editor.redo();  
                    break;  
                case 7:  
                    System.out.println("Current Text: " + editor.getText());  
                    break;  
                case 8:  
                    System.out.println("Exiting...");  
                    scanner.close();  
                    return;  
                default:  
                    System.out.println("Invalid choice! Try again.");  
            }  
        }  
    }  
}  