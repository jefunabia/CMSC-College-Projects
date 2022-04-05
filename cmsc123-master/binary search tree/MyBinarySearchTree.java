public class MyBinarySearchTree {

  private MyTreeNode root;
  private int size;

  public MyBinarySearchTree() {
    this.root = null;
    size = 0;
  }

  
  /*public void add(MyTreeNode root, Integer data) { //CODE V1 SIR WHY DOES THIS WORK?
	  if(this.root == null){
		  this.root = new MyTreeNode(data);
	  }
	  else if(root == null) {
		  root = new MyTreeNode(data);
	  }
	  else if(root.getData().equals(data)){
		  throw new Exception("WOAH it exists!");
	  }
	  else {
		if (root.getData() > data){
			if (root.getLeft() == null){
				root.setLeft(new MyTreeNode(data));
				size++;
			}
			else{
				add(root.getLeft(), data);
			}
		}
		else {
			if (root.getRight() == null){
				root.setRight(new MyTreeNode(data));
				size++;
			}
			else{
				add(root.getRight(), data);
			}
		}
	  }
  }*/
  
  public void add(Integer data) throws Exception {
	  root = add(root, data);
  }
  
  public MyTreeNode add(MyTreeNode root, Integer data) throws Exception{ //CODE V2
	  if(root == null) {
		  return new MyTreeNode(data);
	  }
	  else if(root.getData().equals(data)){
		  throw new Exception("Some data already exists");
	  }
	  else {
		if (data < root.getData()){
				root.setLeft(add(root.getLeft(), data));
				root.getLeft().setParent(root);
			}
		else {
				root.setRight(add(root.getRight(), data));
				root.getRight().setParent(root);
			
		}
	  }
	return root;
  }
  
  public String toString(){
	  return toString(root);
  }
  
  public String toString(MyTreeNode root){
	  if (root == null){
		  return "";
	  }
	  else {
		  String sexyboi = "";
		  sexyboi = sexyboi + toString(root.getLeft()) + " ";
		  sexyboi = sexyboi + String.valueOf(root.getData());
		  sexyboi = sexyboi + toString(root.getRight());
		  return sexyboi;
	  }
  }
  
  public void remove(Integer data) {
	  
  }

  public boolean contains(Integer data) {
    return contains(root, data);
  }

  private boolean contains(MyTreeNode root, Integer data) {
    if (root == null) {
      return false;
    } else {
      if (root.getData() < data) {
        return contains(root.getRight(), data);
      } else if(root.getData() > data)  {
        return contains(root.getLeft(), data);
      } else {
        return true;
      }
    }
  }

  public int size() {
    return size;
  }

  private class MyTreeNode {
    private Integer data;
    private MyTreeNode left;
    private MyTreeNode right;
    private MyTreeNode parent;

    public MyTreeNode(Integer data) {
      this.data = data;
      this.left = null;
      this.right = null;
      this.parent = null;
    }

    public MyTreeNode(Integer data, MyTreeNode left, MyTreeNode right, MyTreeNode parent) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.parent = parent;
    }

    public Integer getData() {
      return data;
    }

    public void setData(Integer data) {
      this.data = data;
    }

    public MyTreeNode getLeft() {
      return left;
    }

    public void setLeft(MyTreeNode left) {
      this.left = left;
    }

    public MyTreeNode getRight() {
      return right;
    }

    public void setRight(MyTreeNode right) {
      this.right = right;
    }

    public MyTreeNode getParent() {
      return parent;
    }

    public void setParent(MyTreeNode parent) {
      this.parent = parent;
    }

  }
}