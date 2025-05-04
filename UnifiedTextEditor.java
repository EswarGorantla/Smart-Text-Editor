import java.util.Scanner;
import java.util.Stack;

class Node {
    String text;
    String action;
    Node prev, next;

    Node(String text, String action) {
        this.text = text;
        this.action = action;
    }
}

public class UnifiedTextEditor {
    private Node head, current;
    private Stack<Node> undoStack = new Stack<>();
    private Stack<Node> redoStack = new Stack<>();

    public UnifiedTextEditor() {
        head = current = new Node("", "START");
    }

    public void appendText(String newText) {
        String combinedText = current.text + newText;                                         //previous text + new text
        Node newNode = new Node(combinedText, "APPEND");
        newNode.prev = current;
        current.next = newNode;
        current = newNode;

        undoStack.push(newNode);
        redoStack.clear();
    }

    
    public void undo() {
        if (!undoStack.isEmpty()) {
            Node lastEdit = undoStack.pop();
            redoStack.push(new Node(lastEdit.text, "UNDO"));
            if (current.prev != null) current = current.prev;
        } else {
            System.out.println("No actions to undo.");
        }
    }

   
    public void redo() {
        if (!redoStack.isEmpty()) {
            Node lastUndo = redoStack.pop();
            undoStack.push(new Node(lastUndo.text, "REDO"));
            if (current.next != null) current = current.next;
        } else {
            System.out.println("No actions to redo.");
        }
    }

  
    public void displayText() {
        System.out.println("\nCurrent Text: " + current.text);
    }

   
    public void showHistory() {
        Node temp = head;
        System.out.println("\nEdit History:");
        while (temp != null) {
            System.out.println(temp.action + ": " + temp.text);
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UnifiedTextEditor editor = new UnifiedTextEditor();
        int choice;

        do {
            System.out.println("\nSmart Text Editor");
            System.out.println("1. Append Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("5. Show History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter text to append: ");
                    String newText = scanner.nextLine();
                    editor.appendText(newText);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayText();
                    break;
                case 5:
                    editor.showHistory();
                    break;
                case 6:
                    System.out.println("Exiting editor.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}