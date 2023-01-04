
public class InfixToPostfix {

    public static int precedence(char c) {
        if (c=='+'||c=='-') return 1;
        if (c=='*'||c=='/'||c=='%') return 2; //modulo
        if (c=='^') return 3;
        if (c=='!') return 4;
        if (c=='=') return 4;
        if (c=='>') return 4;
        if (c=='<') return 4;
        if (c=='&') return 4;
        if (c=='|') return 4;


        return -1;
    }

    public static String change(String infix) {
        //No blanks
        String str = "";
        for (int i=0;i<infix.length();i++) {
            char a = infix.charAt(i);
            if (Character.isLetterOrDigit(a) || a=='(' || a==')' || a=='.' || precedence(a)>0) str += a;
        }
        for (int i=0;i<str.length();i++) {
            //Negative sign
            if (i+1<str.length() && (str.charAt(i)=='-'&&str.charAt(i+1)=='(')) {
                str = str.substring(0,i+1) + "1*" + str.substring(i+1,str.length());
            }
            //coefficient behind paranthesis
            if (i+1<str.length() && (Character.isLetterOrDigit(str.charAt(i+1))&&str.charAt(i)==')')) {
                str = str.substring(0,i+1) + "*" + str.substring(i+1,str.length());
            }
            //coefficent in front of paranthesis
            if (i+1<str.length() && (Character.isLetterOrDigit(str.charAt(i))&&str.charAt(i+1)=='(')) {
                str = str.substring(0,i+1) + "*" + str.substring(i+1,str.length());
            }

        }

        StringBuilder result = new StringBuilder();
        int size = str.length();
        URStack stack = new URStack<>();
        for (int i=0;i<size;i++) {
            char c = str.charAt(i);

            //negative is first in infix
            if ((result.toString().equals("") || (i>0 && str.charAt(i-1)=='(')) && c=='-' && Character.isLetterOrDigit(str.charAt(i+1))) result.append(c);
            //negative follows operator
            if (c=='-' && i>0 && precedence(str.charAt(i-1))>0) result.append(c);
            if (Character.isLetterOrDigit(c)) result.append(c);
            if (!Character.isLetterOrDigit(c) && i>0 && Character.isLetterOrDigit(str.charAt(i-1))) {
                if (c=='.') result.append(c);
                else result.append(" ");
            }
            else if (Character.isLetterOrDigit(c) && i==size - 1) result.append(" ");


            //paranthesis
            if (c=='(') stack.push(c);
            if (c==')') {
                while (!stack.isEmpty()&& (char) stack.peek()!='(') {
                    result.append(stack.pop()).append(" ");
                }
                //pop off LP
                stack.pop();
            }

            //operators
            else if (precedence(c)>0) {
                if (c == '-' && i == 0 || c == '-' && (precedence(str.charAt(i - 1)) > 0 || str.charAt(i - 1) == '(')) stack.peek();
                else {
                    while (!stack.isEmpty() && precedence(c)<=precedence((char) stack.peek())) {
                        result.append(stack.pop()).append(" ");
                    }
                    stack.push(c);
                }
            }
        }


        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        //getting rid of space
        result = new StringBuilder(result.substring(0, result.length() - 1));
        return result.toString();
    }
}
