import static org.junit.Assert.*;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.Test;


public class MyBinarySearchTreeTest extends TestCase {
   MyBinarySearchTree tree;

   @Override
   protected void setUp() throws Exception {
      tree = new MyBinarySearchTree();
   }

   @Test
   public void testAdd() throws Exception {
      tree.add(15);
      assertTrue(tree.contains(15));
   }

  /* @Test
   public void testRemove() throws Exception {
      tree.add(15);
      tree.add(20);
      tree.remove(15);
      assertFalse(tree.contains(15));
      assertTrue(tree.contains(20));
   }*/

   @Test
   public void testContains() throws Exception {
      tree.add(5);
      tree.add(10);
      tree.add(30);
      tree.add(20);
      tree.add(40);
      try{
          tree.add(5);
      }
      catch(Exception e){
          assertEquals(true, tree.contains(5));
          assertEquals(true, tree.contains(30));
          assertEquals(false, tree.contains(16));
      }
   }
   @Test
   public void testToString() throws Exception {
      tree.add(5);
      tree.add(10);
      tree.add(30);
      tree.add(20);
      tree.add(40);
          assertEquals("5 10 20 30 40", tree);
   }


}