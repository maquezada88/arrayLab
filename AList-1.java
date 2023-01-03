import java.util.Arrays;

/**
 * Array-based List implementation
 * 
 * @author Martin Quezada
 * This work complies with the JMU honor code.
 * I received no help on this assignment.
 *
 * @param <E> - Element type to store.
 */
class AList<E> implements List<E> {
  
  
  private E[] listArray; // Array holding list elements
  private static final int defaultSize = 10; // Default size
  private int maxSize; // Maximum size of list
  private int listSize; // Current # of list items
  private int curr; // Position of current element


  /**
   * Create a new list object with maximum size "size".
   * 
   * @param size Initial array size.
   */
  @SuppressWarnings("unchecked") // Generic array allocation
  public AList(int size) {
    maxSize = size;
    listSize = curr = 0;
    listArray = (E[]) new Object[size];
  }

  /**
   * Create a list with the default capacity.
   */
  public AList() {
    this(defaultSize); // Just call the other constructor
  }

  /**
   * Reinitialize the list.
   */
  public void clear() {
    listSize = curr = 0;
  }

  /**
   * Insert item at current position.
   * 
   * @param it The item to insert
   */
  public boolean insert(E it) {
    expand();
    if (listSize >= maxSize) {
      return false;
    }
    
    for (int i = listSize; i > curr; i--) { // Shift elements up
      listArray[i] = listArray[i - 1]; // to make room
    }
    listArray[curr] = it;
    listSize++; // Increment list size
    return true;
  }
  
  public void expand(){//helper method to double the size of the array
    if(listSize==maxSize){
      maxSize=maxSize*2;
      listArray=Arrays.copyOf(listArray, maxSize);
    }
  }
  
  /**
   * Append item to list.
   * 
   * @param it The item to append
   */
  public boolean append(E it) {
    expand();
    if (listSize >= maxSize) {
      return false;
    }
    
    listArray[listSize++] = it;
    return true;
  }

  /**
   * Remove and return the current element.
   */
  public E remove() {
    
    if ((curr < 0) || (curr >= listSize)) { // No current element
      return null;
    }
    
    E it = listArray[curr]; // Copy the element
    
    for (int i = curr; i < listSize-1; i++) { // Shift them down
      listArray[i] = listArray[i+1];
    }
    listSize--;
    int used =listSize/maxSize;
    
    if(used<.25)//checks if array needs to shrink
    {
      listArray=Arrays.copyOf(listArray, (maxSize/2));
    }
    
    return it;
  }

  /**
   * Set position to front.
   */
  public void moveToStart() {
    curr = 0;
  }

  /**
   * Set position to end.
   */
  public void moveToEnd() {
    curr = listSize;
  }

  /**
   * Move position left.
   */
  public void prev() {
    if (curr != 0) {
      curr--;
    }
  }

  /**
   * Move position right.
   */
  public void next() {
    if (curr < listSize) {
      curr++;
    }
  }

  /**
   * Return the list size.
   */
  public int length() {
    return listSize;
  }

  /**
   * Return the current position.
   */
  public int currPos() {
    return curr;
  }

  /**
   * Set the current position to the specified value.
   * 
   * @return false if the position is invalid.
   */
  public boolean moveToPos(int pos) {
    if ((pos < 0) || (pos > listSize)) {
      return false;
    }
    curr = pos;
    return true;
  }


  /**
   * Return true if the position is at the end of the list.
   */
  public boolean isAtEnd() {
    return curr == listSize;
  }

  /**
   * Return the element at the current position.
   */
  public E getValue() {
    if ((curr < 0) || (curr >= listSize)) { // No current element
      return null;
    }
    return listArray[curr];
  }
}
