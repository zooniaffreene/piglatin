public class App {
    public static void main(String[] args)
    {
     
        TestSuite.run();

        Book input = new Book();

        
        input.readFromString("Test", "Dog\nCat\nMouse");

        

        input.printlines(0,2);
        Book output = PigLatinTranslator.translate(input);
        output.printlines(0,2);
    }
}

