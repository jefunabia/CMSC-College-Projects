public class Stack<E> extends LinkedList{
  
  Node<E> top;
  
  public Stack(){
  }
  
  public void push(E item){
    this.add(0,item);
    top = this.getHead().getNext();
  }
  
  public void pop(){  
    this.remove(0);
    top = this.getHead().getNext();
  }
  
  public E peek(){
    try{
      return top.getItem();
    }catch(NullPointerException e){
      System.out.println("List is empty");
    }
    return null;
  }
  
  public boolean isEmpty(){
    return (this.size()==0)?true:false;
  }
  
  public int size(){
    return super.size();
  }
}