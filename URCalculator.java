import java.io.*;
import java.util.Scanner;


public class URCalculator implements InfixCalculator {

    public String evaluate(String exp) {
        String s = InfixToPostfix.change(exp);
        return PostfixCalculator.evaluate(s);
    }
    public static void Write(String[] list,String fileName){
        try {
            FileWriter Writer=new FileWriter(fileName);
            for(int i=0;i<list.length;i++) {
                Writer.write(list[i]+"\n");
            }
            Writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        try {
            URCalculator calculator = new URCalculator();
            File myObj = new File("infix_expr_short.txt");
            Scanner myReader = new Scanner(myObj);
            int counter = 0;
            String[] myString1 = new String[26];
            while (myReader.hasNextLine()) {
                String infix = myReader.nextLine();
                infix = infix.replaceAll("\\s", "");
                String answer = calculator.evaluate(infix);
                myString1[counter] = answer;
                counter++;
            }
            Write(myString1, "output.txt");

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
