package PA4;

import java.util.Scanner;
public class TextEditorApp {
    TextEditor text;

    public TextEditorApp() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a starting string: ");
        String str = input.nextLine();
        text = new TextEditor(str);
        System.out.println("Editing document . . .");

        System.out.println("Editing Menu");
        System.out.println("1. Left");
        System.out.println("2. Right");
        System.out.println("3. insert character");
        System.out.println("4. Delete character");
        System.out.println("5. Get current position");
        System.out.println("6. Move to position");
        System.out.println("7. Display");
        System.out.println("8. Quit");

        System.out.print("Enter an option: ");
        int option = input.nextInt();
        while (option != 8) {
            switch (option) {
                case 1:
                    text.left();
                    System.out.println("Moved cursor left.");
                    break;
                case 2:
                    text.right();
                    System.out.println("Move cursor right.");
                    break;
                case 3:
                    System.out.print("Enter a character: ");
                    char c = input.next().charAt(0);
                    text.insert(c);
                    System.out.println("Inserted character " + c + ".");
                    break;
                case 4:
                    text.delete();
                    System.out.println("Deleted one character.");
                    break;
                case 5:
                    int current = text.current() - 1;
                    System.out.println("Current position: " + current);
                    break;
                case 6:
                    System.out.print("Enter a position: ");
                    int index = input.nextInt();
                    text.move(index);
                    System.out.println("Move to position: " + index);
                    break;
                case 7:
                    System.out.print("String: ");
                    text.display();
                    System.out.println("Length: " + text.size());
                    break;
            }
            System.out.println();
            System.out.print("Enter a new option: ");
            option = input.nextInt();
        }
        System.out.println("Thanks for using my editor program.");
    }

}
