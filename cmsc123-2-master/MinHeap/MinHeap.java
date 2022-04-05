/*
 * Implement a min heap.

The heap should have a max capacity which is a parameter in the constructor
Implement the tree using an array
The heap should accept integers
The heap should have the following methods:
void add(int value) - inserts the value into the heap
int remove() - removes the smallest value in the help and returns it
void preorder() - prints the values in preorder traversal
void inorder() - prints the values in inorder traversal
void postorder() - prints the values in postorder traversal

 */

import java.util.*;

public class MinHeap{
	private int maxCapacity = 10;
	private int size;
	private int[] items;

	public MinHeap(int maxCapacity){
		size = 0;
		items = new int[maxCapacity];
	}

	//HELPER FUNCTIONS
	//RETURNS INDEX
	private int getLeftChildIndex(int parentIndex){ return 2 * parentIndex + 1; }
	private int getRightChildIndex(int parentIndex){ return 2 * parentIndex + 2; }
	private int getParentIndex(int childIndex){ return (childIndex - 1) / 2; }

	//RETURNS TRUE/FALSE
	private boolean hasLeftChild(int index){ return getLeftChildIndex(index) < size; }
	private boolean hasRightChild(int index){ return getRightChildIndex(index) < size; }
	private boolean hasParent(int index){ return getParentIndex(index) >= 0;}

	//RETURNS VALUE
	private int leftChild(int index){ return items[getLeftChildIndex(index)]; }
	private int rightChild(int index){ return items[getRightChildIndex(index)]; }
	private int parent(int index){ return items[getParentIndex(index)]; }

	//SWAPS VALUES
	private void swap(int indexOne, int indexTwo){
		int temp = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = temp;
	}

	//EXPAND IF FULL
	private void expandIfFull(){
		if(size == maxCapacity){
			items = Arrays.copyOf(items, maxCapacity*2);
			maxCapacity *= 2;
		}
	}

	//RETURNS THE MINIMUM ELEMENT
	public int peek(){
		if(size == 0) throw new IllegalStateException("Empty");
		return items[0];
	}

	//RETURNS AND REMOVE THE MINIMUM ELEMENT
	public int remove(){
		if(size == 0) throw new IllegalStateException("Empty");
		int item = items[0];
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return item;
	}

	public void add(int item){
		expandIfFull();
		items[size++] = item;
		heapifyUp();
	}

	//FROM BOTTOM TO TOP, KEEP SWAPPING IF PARENT IS BIGGER
	public void heapifyUp(){
		int index = size - 1; // GETS THE LAST ELEMENT
		while(hasParent(index) && parent(index) > items[index]){ //CHECK IF IT HAS A PARENT, && IF PARENT IS BIGGER
			swap(getParentIndex(index), index); //IF PARENT IS BIGGER, SWAP
			index = getParentIndex(index); //UPDATE INDEX
		}
	}

	public void heapifyDown(){
		int index = 0;
		while(hasLeftChild(index)){ //ONLY CHECK IF HAS LEFT CHILD, BECAUSE IF THERE'S NO LEFT CHILD, THEN THERE IS CERTAINLY NO RIGHT CHILD.

			//THIS SNIPPET WILL GET THE INDEX OF WHO HAS A SMALLER VALUE BETWEEN THE LEFT & RIGHT CHILD
			int smallerChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && rightChild(index) < leftChild(index)){
				smallerChildIndex = getRightChildIndex(index);
			}

			//COMPARES THE SMALLER VALUE TO THE CURRENT ITEM IN INDEX
			if(items[index] < items[smallerChildIndex]){ // IF TRUE, HEAP IS ORDERED
				break;
			}
			else{ // OTHERWISE, SWAP THE VALUES
				swap(index, smallerChildIndex);
				index = smallerChildIndex; //UPDATE THE INDEX
			}
		}
	}



	public void preorder(){
		if(size == 0) throw new IllegalStateException();
		int index = 0;
		traversePreOrder(index);
	}

	public void inorder(){
		if(size == 0) throw new IllegalStateException();
		int index = 0;
		traverseInOrder(index);
	}

	public void postorder(){
		if(size == 0) throw new IllegalStateException();
		int index = 0;
		traversePostOrder(index);
	}


	//RECURSION HELPER FUNCTION
	private void traversePreOrder(int index){
		System.out.print(items[index]);
		if(hasLeftChild(index)) traversePreOrder(getLeftChildIndex(index));
		if(hasRightChild(index)) traversePreOrder(getRightChildIndex(index));
	}

	private void traverseInOrder(int index){
		if(hasLeftChild(index)) traverseInOrder(getLeftChildIndex(index));
		System.out.print(items[index]);
		if(hasRightChild(index)) traverseInOrder(getRightChildIndex(index));
	}

	private void traversePostOrder(int index){
		if(hasLeftChild(index)) traversePostOrder(getLeftChildIndex(index));
		if(hasRightChild(index)) traversePostOrder(getRightChildIndex(index));
		System.out.print(items[index]);
	}

	public static void main(String[] args){
		MinHeap heap = new MinHeap(10);
		heap.add(1);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(5);
		heap.preorder();
		System.out.println("");
		heap.inorder();
		System.out.println("");
		heap.postorder();
	}

}