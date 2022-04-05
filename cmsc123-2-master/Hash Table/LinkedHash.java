import java.util.LinkedList;

public class LinkedHash {

	private Node head;
	private int size;

	public LinkedHash(){
		head = new Node(null,0);
		size = 0;
	}

	public Node getHead(){
		return this.head;
	}

	public int getSize(){
		return this.size;
	}

	public boolean isEmpty(){
		return (size==0);
	}

	public void add(String key, int value){
		Node temp = new Node(key,value);
		temp.setNext(head.getNext());
		head.setNext(temp);
		size++;
	}

	public Node remove(String key){
		Node current = head.getNext();
		Node prev = head;

		while(current!=null){
			if(current.getKey() == key){
				prev.setNext(current.getNext());
				size--;
				return current;
			}
			prev = prev.getNext();
			head = head.getNext();
		}
		return null;
	}

	public boolean contains(String key){
		Node current =  head.getNext();

		while(current!=null){
			if(current.getKey() == key){
				return true;
			}
			current = current.getNext();
		}

		return false;
	}

	public Node get(String key){
		Node current =  head.getNext();

		while(current!=null){
			if(current.getKey() == key){
				return current;
			}
			current = current.getNext();
		}

		return null;
	}

	public void display(){
		Node current = head.getNext();
		while(current!=null){
			System.out.print(current.getValue()+" ");
			current = current.getNext();
		}
	}
	
}
