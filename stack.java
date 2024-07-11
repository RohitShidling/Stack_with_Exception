//package stack_with_Exception;

import java.util.Scanner;

interface Istack {
    public void push(int data) throws overflowexception;

    public void pop() throws underflowexception;
}

class overflowexception extends RuntimeException {
    public String toString() {
        return "Stack full exception";
    }
}

class invalidstacksize extends RuntimeException {
    public String toString() {
        return "Invalid stack size";
    }
}

class underflowexception extends RuntimeException {
    public String toString() {
        return "Stack empty exception";
    }
}

class stack implements Istack {
    int arr[], top = -1;

    stack() {
        this(5);
    }

    stack(int size) {
        if (size <= 0) {
            throw new invalidstacksize();
        }
        arr = new int[size];
    }

    @Override
    public void push(int data) throws overflowexception {
        if (top == arr.length - 1) {
            throw new overflowexception();
        }
        top++;
        arr[top] = data;
    }

    @Override
    public void pop() throws underflowexception {
        if (top == -1) {
            throw new underflowexception();
        }
        top--;
    }
}

public class rohit {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Istack s = null;
        boolean validStack = false;

        // Loop to get valid stack size
        while (!validStack) {
            try {
                System.out.print("Enter stack size: ");
                int size = scan.nextInt();
                if (size <= 0) {
                    throw new invalidstacksize();
                }
                s = new stack(size);
                validStack = true;
            } catch (invalidstacksize e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.next(); // Clear the invalid input
            }
        }

        int ch;
        do {
            System.out.print("1 -> push 2 -> pop 3 -> exit: ");
            ch = scan.nextInt();
            switch (ch) {
                case 1:
                    try {
                        System.out.print("Enter value to insert: ");
                        int data = scan.nextInt();
                        s.push(data);
                    } catch (overflowexception e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    try {
                        s.pop();
                    } catch (underflowexception e) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Incorrect choice. Try again.");
            }
        } while (ch != 3);

        scan.close();
    }
}
