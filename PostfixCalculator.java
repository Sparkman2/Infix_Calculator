public class PostfixCalculator {

    public static String evaluate(String postfix) {
        int size = postfix.length();
        URQueue queue = new URQueue<>();
        URStack stack= new URStack<>();
        String stri = "";
        double answer = 0;

        //make the queue
        for (int i=0;i<size;i++) {
            char c = postfix.charAt(i);
            if (c==' ') {
                queue.enqueue(stri);
                stri = "";
            }
            //operand
            if (Character.isLetterOrDigit(c) || c=='.') {
                stri += c;
            }
            //operator
            if (InfixToPostfix.precedence(c)>0) {
                stri += c;
            }
            if (i+1==size) {
                queue.enqueue(stri);
                stri = "";
            }
        }

        while (!queue.isEmpty()) {
            char d = ((String) queue.peek()).charAt(0);
            if (Character.isLetterOrDigit(d) || (((String) queue.peek()).length()>1 && Character.isLetterOrDigit(((String) queue.peek()).charAt(1)))) {
                stack.push(queue.dequeue());
            }
            else {
                double v2 = Double.parseDouble((String) stack.pop());
                if (((String) queue.peek()).equals("!")){
                    if (v2 == 1){
                        answer = (double) 0;
                    } else{
                        answer = (double) 1;
                    }
                    break;
                }
                double v1 = Double.parseDouble((String) stack.pop());
                //Casework depending on the identity of the operator
                if (((String) queue.peek()).equals("+")){
                    answer = (double) v1 + v2;
                }
                else if (((String) queue.peek()).equals("-")){
                    answer = (double) v1 - v2;
                }
                else if (((String) queue.peek()).equals("*")){
                    answer = (double) v1 * v2;
                }
                else if (((String) queue.peek()).equals("/")){
                    answer = (double) v1 / v2;
                }
                //modulo
                else if (((String) queue.peek()).equals("%")){
                    answer = (double) v1 % v2;
                }
                else if (((String) queue.peek()).equals("^")){
                    answer = (double) Math.pow(v1,v2);
                }
                else if (((String) queue.peek()).equals("<")){
                    if(v1 < v2){
                        answer = (double) 1;
                    }else{
                        answer = (double) 0;
                    }
                }
                else if (((String) queue.peek()).equals(">")){
                    if(v1 > v2){
                        answer = (double) 1;
                    }else{
                        answer = (double) 0;
                    }
                }
                else if (((String) queue.peek()).equals("=")){
                    if(v2 == v1){
                        answer = (double) 1;
                    } else{
                        answer = (double) 0;
                    }
                }else if (((String) queue.peek()).equals("&")){
                    if(v1 == v2){
                        answer = (double) 1;
                    }else{
                        answer = (double) 0;
                    }
                }
                else if (((String) queue.peek()).equals("|")) {
                    if (v1 == v2) {
                        answer = (double) v1;
                    } else {
                        if (v2 > v1) {
                            answer = (double) v2;
                        } else {
                            answer = (double) v1;
                        }
                    }
                }
                stack.push(Double.toString(answer));
                queue.dequeue();
            }
        }
        return (String) stack.pop();
    }
}
