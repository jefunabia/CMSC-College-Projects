public class Node<E>{

 private E item;
 private Node<E> next;

 public Node(){
  this(null,null);
 }

 public Node(E item){
  this(item,null);
 }

 public Node(E item,Node next){
  this.item=item;
  this.next=next;
 }

 public void setItem(E item){
  this.item=item;
 }

 public void setNext(Node next){
  this.next=next;
 }

 public E getItem(){
  return this.item;
 }

 public Node<E> getNext(){
  return this.next;
 }
}