public class Queue{
  public int[] array;
  public int size;
  public int count;
  public int front;
  public int rear;
  
  public Queue(int size){
    array = new int[size];
    this.size = size;
    count = 0;
    front = 0;
    rear = 0;
  }
  
  public void enqueue(int item){
    switch(isFull()){
      case 1:{
        System.out.println("Queue is full. Expanding...");
        this.size *= 2; //duplicates the size
        int[] tempArray = new int[this.size]; //creates a temporary array
        for(int i=0;i<count;i++){ //transfers all contents into temporary array
          tempArray[i] = this.array[i];
        }
        tempArray[count++] = item; // adds current item
        this.array = tempArray; // points this.array to tempArray
        rear = count;
        front = 0;
        break;
      }
      case 2:{
        System.out.println("Queue is full. Expanding...");
        this.size*=2;
        int[] tempArray = new int[this.size];
        for(int i=0;i<count;i++){
          tempArray[i] = array[front];
          front = (front + 1) % size;
        }
        tempArray[count++] = item; // adds current item
        this.array = tempArray; // points this.array to tempArray
        rear = count;
        front = 0;
        break;
      }
      default:{
        array[rear] = item;
        rear = (rear + 1) % size;
        count++;
        break;
      }
    }
    
  }
  
  public void dequeue(){
    if(isEmpty()){
      System.out.println("Queue is empty");
    }
    else{
      array[front] = 0;
      front = (front + 1) % size;
      count--;
    }
    
    
  }
  
  private int isFull(){
    if(front == 0 && rear == size - 1){
      return 1; //Case 1
    }
    else if(front == rear + 1){
      return 2;//Case 2
    }
    return 0;
  }
  
  public void display(){
    for(int i=0; i<size; i++){
      System.out.println(array[i]);
    }
  }
  
  public int peek(){
    return array[front];
  }
  
  public boolean isEmpty(){
    return (count==0)?true:false;
  }
  
  public int getCount(){
    return count;
  }
  
  public int size(){
    return size;
  }
  
  public int getFront(){
    return front;
  }
  
  public int getRear(){
    return rear;
  }
}