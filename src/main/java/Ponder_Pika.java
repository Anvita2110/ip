import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Task.Event;
import Task.Deadline;
import Task.Task;
import Task.Todo;

public class Ponder_Pika {

    private final List<Task> myList = new ArrayList<>();

    public void printDivider() {
        System.out.println("...........................................................");
    }

    private void greet() {
        String logo = "Ponder_Pika";

        System.out.println("----------------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        System.out.println("\nIt is a great day to ponder! How may I help you?");
        System.out.println("----------------------------------------------------------");
    }

    private static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public void echo() {
        greet();
        Scanner scan = new Scanner(System.in);
        String userCommand;

        while(scan.hasNext()) {
            userCommand = scan.nextLine().trim();

            String[] commands = userCommand.split(" ", 2);

            switch (commands[0].toLowerCase()) {
                case "list":
                    for (int i = 0; i < myList.size(); i++) {
                        System.out.println(i+1 + ". " + myList.get(i).toString());
                    }
                    printDivider();
                    break;
                case "bye":
                    System.out.println("------------------------------------------------------------");
                    Ponder_Pika.bidBye();
                    System.out.println("\n----------------------------------------------------------");
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(commands[1]);
                    myList.get(markIndex - 1).markDone();
                    System.out.println("Your task has been marked as done.");
                    System.out.println(myList.get(markIndex- 1).toString());
                    printDivider();
                    break;
                case "unmark":
                    int unMarkIndex = Integer.parseInt(commands[1]);
                    myList.get(unMarkIndex - 1).markUndone();
                    System.out.println("Your task has been undone.");
                    System.out.println(myList.get(unMarkIndex - 1).toString());
                    printDivider();
                    break;
                case "todo":
                    Task todo = new Todo(commands[1]);
                    myList.add(todo);
                    System.out.println("        Pika! I have added your todo: " + commands[1]);
                    printDivider();
                    break;
                case "deadline":
                    String[] args = commands[1].split("/by ");
                    Task deadline = new Deadline(args[0].trim(), args[1].trim());
                    myList.add(deadline);
                    System.out.println("        Pika! I have added a deadline: " + args[0].trim() + " by " + args[1].trim());
                    printDivider();
                    break;
                case "event":
                    String eventDetails = commands[1].trim();
                    String[] desc = eventDetails.split("/from");
                    String detail = desc[1].trim();
                    String[] time = detail.split("/to");
                    Task event = new Event(desc[0].trim(), time[0].trim(), time[1].trim());
                    myList.add(event);
                    System.out.println("        Pika! I have added your event: " + desc[0].trim() + " from " + time[0].trim() + " to " + time[1].trim() );
                    printDivider();
                    break;
                default:
                    System.out.println("Invalid command");
            }

            if(userCommand.equals("bye")) {
                break;
            }
        }

    }
    public static void main(String[] args) {
        Ponder_Pika bot = new Ponder_Pika();
        bot.echo();
    }
}
