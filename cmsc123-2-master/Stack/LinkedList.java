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
   if(i > size){
    throw new IndexOutOfBoundsException("Index out of bounds");
   }
  }
  
  public void add(int index, E item){
    checkException(index);
    int i = 0;
    Node<E> temp = new Node<E>(item);
    Node<E> prev = head;
    Node<E> current = head.getNext();

    while(current!=null){
     if(i == index){
      break;
     }
     i++;
     prev=prev.getNext();
     current=current.getNext();
    }
    
    prev.setNext(temp);
    temp.setNext(current);
    
    if(isEmpty() || index == size){
      tail.setNext(temp);
    }
    size++;
  }

  public void display(){
   if(isEmpty()){
     System.out.println("List is empty");
   }
   else{
     Node<E> current = head.getNext();
     int index = 0;
     while(current!=null){
     System.out.println(index +".) "+current.getItem());
     index++;
     current=current.getNext();
     }
   }
   
  }

  public void remove(int index){
   checkException(index);
   
   try{

     Node<E> prev = head;
     Node<E> current = head.getNext();
     int i = 0;
     
     while(current!=null){
       if(i == index){
         break;
       }
       i++;
       prev=prev.getNext();
       current=current.getNext();
     }
     
     if(current.getNext()==null){ //Last element
       prev.setNext(null);
       tail.setNext(prev);
     }
     else{
       prev.setNext(current.getNext());
     }
     
     size--;
   }catch(NullPointerException e){
     System.out.println("List is empty");
   }
  }

  public int size(){
   return this.size;
  }

  public Iterator iterator(){
   return new LinkedListIterator(this);
  }

  private boolean isEmpty(){
   return (size==0)?true:false;
  }

 }