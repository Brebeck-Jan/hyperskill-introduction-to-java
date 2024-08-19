import java.util.Scanner;

public class Test {
    // Creating class
    public static class Book {
        // private properties
        private String title;
        private String author;
        private int numberOfPages;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getNumberOfPages() {
            return numberOfPages;
        }

        public void setNumberOfPages(int numberOfPages) {
            if (numberOfPages > 0) {
                this.numberOfPages = numberOfPages;
            }
        }

        // getters and setters go here
        // Remember:
        // 1. They must not allow empty string for 'title' and 'author'.
        // 2. They must not allow negative or zero value for 'numberOfPages'.
        // 3. If such values are attempted to be set, the property should remain unchanged.
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an object of the Book class
        Book book = new Book();

        String title = scanner.nextLine();
        String author = scanner.nextLine();
        int numberOfPages = scanner.nextInt();

        book.setTitle(title);
        book.setAuthor(author);
        book.setNumberOfPages(numberOfPages);

        System.out.println(book.title);
        System.out.println(book.author);
        System.out.println(book.numberOfPages);
        // Take Title, Author and numberOfPages as next inputs and set them using the mutator methods
        // Your code here

        // Then use the accessor methods to get and print these values.
        // Your code here

        scanner.close();
    }

import java.util.Scanner;

    // Define the Circle class here
    class Circle {
        int radius = 1;
        int area;
        // Declare the properties of the Circle class here

        Circle() {
            this.radius = 1;
            this.area = (int) Math.round(Math.pow(1 * Math.PI, 2));;
        }

        Circle(int radius) {
            this.radius = radius;
            this.area = (int) Math.round(Math.pow(radius * Math.PI, 2));
        }

    /* Define the default and parameterized constructors here. The constructors should initialize the
       'radius' property with the given or default value and 'area' property with the value calculated as πr^2
        rounded off to the nearest whole number. */


        // Getters for the 'radius' and 'area' properties
        public int getRadius() {
            return this.radius;
            // Your code here
        }

        public int getArea() {
            return this.area;
            // Your code here
        }
    }

    // update the class
    class BadRequestException extends IllegalArgumentException{
        BadRequestException(String cause){
            super(cause);

        }
    }
}