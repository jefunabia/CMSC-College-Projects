import java.util.*;

public class LinkedList<E> implements Collection<E> {

 private int size;
 private Node<E> head;
 private Node<E> tail;

 public LinkedList(){
  head = new Node<E>();
  tail = new Node<E>();
  size = 0;
 }

 private void checkException(int i){
  if(i >= size){
   throw new IndexOutOfBoundsException("Index out of bounds");
  }
 }

 public void add(int index, E item){
  checkException(index);
  Node<E> temp = new Node<E>(item);
  Node<E> prev = head;
  Node<E> current = head.getNext();
  int i = 0;
  
   while(current!=null){
    if(i == index){
     prev.setNext(temp);
     temp.setNext(current);
     break;
    }
    i++;
    prev = prev.getNext();
    current = current.getNext();
   }
  size++;
 }

 public void display(){
  Node<E> current = head.getNext();
  int index = 0;
  
  while(current!=null){
   System.out.println(index +".) "+current.getItem());
   index++;
   current=current.getNext();
  }
 }

 public void remove(int index){
  checkException(index);
  Node<E> prev = head;
  Node<E> current = head.getNext();
  int i = 0;
  
  while(current!=null){
   if(i == index){
    if(current == tail.getNext()){
     prev.setNext(current.getNext());
     current.setNext(null);
     tail.setNext(prev);
    }
    else{
     prev.setNext(current.getNext());
     current.setNext(null);
    }
    break;
   }
   i++;
   prev=prev.getNext();
   current=current.getNext();
  }
  size--;
 }

 public Node<E> getHead(){
  return this.head;
 }

 public Node<E> getTail(){
   return this.tail;
 }

 public int size(){
  return this.size;
 }

 public Iterator iterator(){
  return new LinkedListIterator(this);
 }

}