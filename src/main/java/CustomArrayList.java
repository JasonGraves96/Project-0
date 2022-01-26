
import java.util.Arrays;
//I think this is all filled out correctly. Though it may not be, the program doesn't use arraylist extensively and only makes use of the .get and .add methods
/**
 * A fairly simple arraylist implementation extending custom list interface.
 * Default size is 2, grows by size * 2 when needed.
 * When an element is added or removed at an index other elements are not re-arranged.
 *
 * intellij didn't like the comment that was here
 */
public class CustomArrayList<E> implements CustomListInterface<E> {
    private Object[] array;
    private int size;
    private int maxSize;

    /**
     * Default constructor, creates an empty underlying array with maxSize 2
     */
    public CustomArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    /**
     * Sized constructor, creates an empty object with maxSize size
     * @param size the initial size of the underlying array
     */
    public CustomArrayList(int size) {
        maxSize = size;
        size = 0;
        array = new Object[size];
    }

    /**
     * Element list constructor, takes in variable number of objects and creates an underlying
     * array large enough to fit them.
     * @param e
     */
    public CustomArrayList(E ...e) {
        maxSize = size = e.length;
        array = new Object[size];

        for (int i = 0; i < size; ++i) {
            array[i] = e[i];
        }
    }


    /**
     * Adds an object to the underlying array after all previously added objects.
     * If array needs to grow, it invokes grow method.
     * @param o object to be added
     */
    @Override
    public void add(Object o) {
        //Implement this method
        // NOTE: if size >= maxSize we need to grow array
        if(this.size>=this.maxSize)
            growArray();
        this.array[this.size]=o;
        this.size++;
    }

    /**
     * Adds object at specified index, advancing the size of the underlying array. This will
     * overwrite an existing element, rather than shift them
     * @param index index location where object will be inserted
     * @param e element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(E e, int index) throws IndexOutOfBoundsException {
        //Implement this method
        this.array[index] = e;
    }

    /**
     * gets the object located at supplied index
     * @param index index of object to get
     * @return object located at index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        //Implement this method
        return (E) this.array[index];
    }

    /**
     * Emptys the underlying array by setting it's private reference to null and allowing
     * the old array to be garbage collected.
     */
    @Override
    public void clear() {
        //Implement this method
       this.array  = null;
    }

    /**
     * Check if object o is found within underlying array, using Object.equals() method
     * @param o object to search for
     * @return index location of first instance of matching object. -1 if not found.
     */
    @Override
    public int contains(Object o) {
        //Implement this method
        for(int i=0;i<this.size;i++)
            if((o.equals(array[i])))
                return i;
        return -1;
    }

    /**
     * Removes object at specified index from underlying array, setting to null.
     * @param index index of object to remove from array
     */
    @Override
    public void remove(int index) {
        //Implement this method
        //this is fucked come back to it
        int numberOfNulls = 0;
        this.array[index] = null;
        for(int i=0;i<size;i++)
            if(this.array[i]== null)
                numberOfNulls++;
                else
                    this.array[i-numberOfNulls] = (this.array[i]);
        size--;
    }

    /**
     * returns size of array. This is the one greater than the index of the most advanced stored object,
     * not the maxSize which controls growth of the underlying array.
     * @return one greater than index of most advanced stored object
     */
    @Override
    public int size() {
        //Implement this method
        return this.size;
    }


    /**
     * Doubles the size of the underlying array by creating a new array and copying the
     * contents of the previous array into it.
     */
    private void growArray(){
        //System.out.println("Growing Array from " + maxSize + " to " + maxSize * 2);
        //set up new array
        maxSize = maxSize * 2;
        Object[] tempArray = array;
        array = new Object[maxSize];

        //copy to new array
        for (int i = 0; i < size; i++) {
            array[i] = tempArray[i];
        }
    }
}
