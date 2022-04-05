import java.util.LinkedList;
import java.util.Iterator;

public class OpenHash{
	public static int DEFAULT_SIZE = 13;
	public int size;
	public LinkedHash[] array;

	public OpenHash(){
		this.size = DEFAULT_SIZE;
		array = new LinkedHash[size];
		for(int i = 0; i<size; i++){
			array[i] = new LinkedHash();
		}
	}

	private int hash(String key){
		return key.charAt(0) % size;
	}

	public void put(String key, int value){
		array[hash(key)].add(key,value);
	}

	public Node get(String key){
		return array[hash(key)].get(key);
	}

	public Node remove(String key){
		return array[hash(key)].remove(key);
	}

	public boolean containsKey(String key){
		return array[hash(key)].contains(key);
	}

	public LinkedList<Integer> values(){
		LinkedList<Integer> list = new LinkedList<Integer>(); // Accumulation of all values
		Node current;
		for(int i = 0; i<size; i++){
			current = array[i].getHead().getNext();
			while(current!=null){
				list.add(current.getValue());
				current = current.getNext();
			}
		}
		return list;
	}

	public LinkedList<String> keys(){
		LinkedList<String> list = new LinkedList<String>(); // Accumulation of all values
		Node current;
		for(int i = 0; i<size; i++){
			current = array[i].getHead().getNext();
			while(current!=null){
				list.add(current.getKey());
				current = current.getNext();
			}
		}
		return list;
	}

	public void display(){
		for(int i = 0; i<size; i++){
			System.out.print("["+i+"] = ");
			array[i].display();
			System.out.print("\n");
		}
	}

	public static void main(String[] args){
		OpenHash hash = new OpenHash();
		hash.put("Apple",12);
		hash.put("Apple",19 );
		hash.put("Orange",17);
		LinkedList<String> list = hash.keys();
		Iterator iter = list.iterator();

		while(iter.hasNext()){
			System.out.println(iter.next());
		}

		//hash.display();

	}


}