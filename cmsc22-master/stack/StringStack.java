/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author localuser
 */
import java.util.*;

public class StringStack {
    public static final int DEFAULT_SIZE = 10;
    
    private static String[] elements;
    private static int count;
    private String currentItem = "";
    private static int maxSize = 10;
    private String retString;
    
    public StringStack() {
        elements = new String[DEFAULT_SIZE];
    }
    
    public StringStack(int size) {
        elements = new String[size];
        maxSize = size;
    }
    
    public void push(String item) {
        if(count >= maxSize) {
            elements = Arrays.copyOf(elements, maxSize + 5);
            maxSize += 5;
        }
        currentItem = item;
        elements[count] = item;
        count++;
    }
    
    public String pop() {
        if(count > 0) {
            retString = elements[count - 1];
            elements[count - 1] = "";
            count--;
            return retString;
        }
        else {
            throw new ArrayIndexOutOfBoundsException("count out of bounds"); 
        }
    }
    
    public int size() {
        return count;
    }
    
    public String toString() {
        if (size() == 0) {
            return "EMPTY";
        }
        String str = "";
        for (int i = 0; i < count - 1; i++) {
            str += elements[i] + ", ";
        }
        return str + elements[size() - 1];
    }
    
    public String peek() {
        return currentItem;
    }
    
    public static void main(String[] args) {
        StringStack stack = new StringStack(2);
        stack.push("paper");
        stack.push("plastic");
        stack.push("tissue");
        stack.push("mouse");
        System.out.println(stack.peek());
        stack.push("awaw");
        System.out.println(stack);
        System.out.println(stack.size());
        
        String item1 = stack.pop();
        String item2 = stack.pop();
        String item3 = stack.pop();
        String item4 = stack.pop();
        String item5 = stack.pop();
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);
        System.out.println(item5);
        System.out.println(stack);
        System.out.println(stack.size());
        
    }
}
