import java.util.*;

public class LinkedListIterator<E> implements Iterator {

 private LinkedList list;
 private Node<E> prev;
 private Node<E> current;
 private Node<E> next;
 
 public LinkedListIterator(LinkedList list){
  this.list = list;
  prev = list.getHead();
  current = null;
  next = list.getHead().getNext();
  
 }

 public boolean hasNext(){
   return (next != null)?true:false;
 }

 public E next(){
   if(next == null) throw new NoSuchElementException();
   if(current!=null){
     prev = current;
   }
   current = next;
   next = next.getNext();
   
   return current.getItem();
 }

}