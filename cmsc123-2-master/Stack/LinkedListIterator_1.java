public class LinkedListIterator<E> implements Iterator {

 private LinkedList list;
 private Node<E> prev;
 private Node<E> current;
 private Node<E> next;

 public LinkedListIterator(LinkedList list){
  prev = list.getHead();
  current = null;
  next = list.getHead().getNext();
  this.list = list;
 }

 public boolean hasNext(){
  return next != null;
 }

 public E next(){
  if(current!=null){
   prev = current;
  }
  current = next;
  next = current.getNext();

  return current.getItem();
 }
}