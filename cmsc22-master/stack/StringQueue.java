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

public class StringQueue {
    private static String[] elements = new String[1];
    private static int count;
    
    public StringQueue() {
        //asdads
    }
    
    public int size() {
        return count;
    }
    
    public void enQueue(String item) {
        
        elements = Arrays.copyOf(elements, elements.length + 1);
        elements[count] = item;
        count++;
    }
    
    public String deQueue(int x) {
        String temp = "";
        String newString[] = new String[elements.length - x];
        for(int i = 0; i < x - 1; i++) {
            temp += elements[i] + ", ";
        }
        temp += elements[x - 1];
        for(int a = 0; a < x; a++) {
            newString[a] = elements[a + x];
        }
        elements = Arrays.copyOf(newString, newString.length);
        return temp;     
    }
    
    public String peek() {
        return elements[elements.length - 1];
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
    
    public void singit(int position, String item) {
        String[] newString = new String[position + 1];
        for(int i = 0; i < position; i++) {
            newString[i] = elements[i];
        }
        newString[position - 1] = item;
        for(int a = position; a < elements.length + 1; a++) {
            newString[a] = elements[a];
        }
        elements = Arrays.copyOf(newString, newString.length);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StringQueue line = new StringQueue();
        line.enQueue("something");
        line.enQueue("anything");
        line.enQueue("nothing");
        System.out.println(line);
        line.deQueue(1);
        System.out.println(line);
        System.out.println(line.peek());
    }
    
}
