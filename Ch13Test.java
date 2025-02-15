import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.lang.*;
/**
 * JUnit Test class for Searching and Sorting
 */
public class Ch13Test {
	/**
	 * Base lists
	 */
	static List<Integer> sorted, reverse, empty, single, random, sorted2, copy, temp; 
	
	/**
	 * Default constructor not used, added so Javadoc won't complain.
	 */
	public Ch13Test(){}
	
	/**
	 * Reset the base lists just in case
	 */
	@BeforeEach 
	void reset(){
		sorted = new ArrayList<>(Arrays.asList(9001,9002,9003,9004,9005));
		reverse = new ArrayList<>(Arrays.asList(9005,9004,9003,9002,9001));
		empty = new ArrayList<>();
		single = new ArrayList<>(Arrays.asList(9003));
		random = new ArrayList<>();
		Random rand = new Random(12345);
		for(int i = 0; i < 20; i++){
			random.add(rand.nextInt(9001, 9100));
		}
		sorted2 = new ArrayList<>(random);
		Collections.sort(sorted2);
	}
	
	/**
	 * Tests iterative binary search using sorted list
	 */
	@Test 
	public void testItrBinary() throws IllegalArgumentException{
		copy = new ArrayList<>(sorted);
		assertTrue(itrBinary(sorted, 9003), "contained this value");
		assertEquals(copy, sorted, "list should remain unchanged");
		assertFalse(itrBinary(sorted, -1), "did not contain this value");
		assertThrows(IllegalArgumentException.class, ()->{itrBinary(null, 3);}, "null is not a valid parameter");
	}
	
	/**
	 * Tests descending bubble using the base lists
	 */
	@Test 
	public void testDescendBubble() throws IllegalArgumentException{
		assertEquals(empty, descendBubble(empty), "empty list should not break things");
		assertEquals(single, descendBubble(single), "single list should not break things");
		copy = new ArrayList<>(reverse);
		temp = descendBubble(reverse);
		assertEquals(copy, reverse, "reverse list should still remain reversed");
		assertEquals(reverse, temp, "reverse list should not break things");
		assertEquals(reverse, descendBubble(sorted), "sorted should not break things");
		sorted2.sort(Comparator.reverseOrder());
		assertEquals(sorted2, descendBubble(random), "random list should be sorted");
		assertThrows(IllegalArgumentException.class, ()->{descendBubble(null);}, "null is not a valid parameter");
	}
	
	/**
	 * Tests selectionHi using the base lists
	 */
	@Test 
	public void testSelectionHi() throws IllegalArgumentException{
		assertEquals(empty, selectionHi(empty), "empty list should not break things");
		assertEquals(single, selectionHi(single), "single list should not break things");
		copy = new ArrayList<>(reverse);
		temp = selectionHi(reverse);
		assertEquals(copy, reverse, "reverse list should remain reversed");
		assertEquals(sorted, temp, "reverse list should not break things");
		assertEquals(sorted, selectionHi(sorted), "sorted list should not break things");
		assertEquals(sorted2, selectionHi(random), "random list should be sorted");
		assertThrows(IllegalArgumentException.class, ()->{selectionHi(null);}, "null is not a valid parameter");
	}
	
	/**
	 * Tests randomQuick using the base lists
	 */
	@Test 
	public void testRandomQuick() throws IllegalArgumentException{
		assertEquals(empty, randomQuick(empty), "empty list should not break things");
		assertEquals(single, randomQuick(single), "single list should not break things");
		copy = new ArrayList<>(reverse);
		temp = randomQuick(reverse);
		assertEquals(copy, reverse, "original list should remain unchanged");
		assertEquals(sorted, temp, "reverse list should not break things");
		assertEquals(sorted, randomQuick(sorted), "sorted list should not break things");
		assertEquals(sorted2, randomQuick(random), "sorted list should not break things");
		assertThrows(IllegalArgumentException.class, ()->{randomQuick(null);}, "null is not a valid parameter");
	}
	
	/**
	 * An iterative implementation of binary search.
	 * 
	 * Precondition: list must be sorted.
	 * 
	 * @param sorted The list to be searched
	 * @param number The number to find
	 * @return True if the number exists in the list, false otherwise
	 * @throws IllegalArgumentException if the list is null
	 */
	public static boolean itrBinary(List<Integer> sorted, int number) throws IllegalArgumentException{
		if(sorted == null)throw new IllegalArgumentException();
		
		int start = 0, end = sorted.size() - 1;
		
		while(start <= end){
			int mid =start +(end - start)/ 2;
			
			if(sorted.get(mid) == number) return true;
			if (sorted.get(mid)< number){
				start = mid + 1;//start in the middle to end
			}else{
				end = mid- 1;//end in middle ans start from beginning
			}
		}
		return false;

	}

	/**
	 * Sorts a list using bubble sort in descending order rather than ascending order.
	 * 
	 * @param list The list that needs to be sorted.
	 * @return The sorted list
	 * @throws IllegalArgumentException if the list is null
	 */
	public static List<Integer> descendBubble(List<Integer> list) throws IllegalArgumentException{
		if(list == null)throw new IllegalArgumentException();
		List<Integer> result = new ArrayList<>(list);
		for(int i = 0; i < list.size();i++){
		
			for(int j = 0; j < list.size() -1 -i; j++){
				if(list.get(j) < list.get(j+1)){
					swap(list, j, j+1);
		}//referenced from class code
	}
}
		return new ArrayList<>(list);
	}
	private static void swap(List<Integer> list, int i, int j){
		int temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
}
	
	/**
	 * Sorts a list by selecting for the highest value each iteration.
	 * 
	 * @param list The list that needs to be sorted.
	 * @return The sorted list
	 * @throws IllegalArgumentException if the list is null
	 */
	public static List<Integer> selectionHi(List<Integer> list) throws IllegalArgumentException{
		if(list == null)throw new IllegalArgumentException("Input list can't be null");
		List<Integer> listx = new ArrayList<>(list);
		for (int i = listx.size()-1; i > 0; i--) {		
			int index = i;
			for(int j = 0; j < i; j++){
				if(listx.get(index) < listx.get(j)){//ascending
					index = j;
	}
}

		swap(listx, index, i);
}
	System.out.println(listx);
	return listx;
	//return new ArrayList<Integer>(list);
	}
	
	
	/**
	 * Sorts a list using quicksort with a random pivot.
	 * 
	 * @param list The list that needs to be sorted.
	 * @return The sorted list
	 * @throws IllegalArgumentException if the list is null
	 */
	public static List<Integer> randomQuick(List<Integer> list) throws IllegalArgumentException{
		if (list == null) throw new IllegalArgumentException();
		if(list.size() < 2) return list; // well defined base case
		List<Integer> left, right, temp = new LinkedList<>(list);
		left = new LinkedList<>();
		right =new LinkedList<>();
		Random rand = new Random();
		int pivotindex = rand.nextInt(temp.size());//random generator for pivotindex
		Integer pivot = temp.remove(pivotindex);
		//Integer pivot = temp.removeFirst();
		Iterator<Integer> itr = temp.iterator();
		while(itr.hasNext()){
			Integer i = itr.next();
			if(i.compareTo(pivot) <= 0){
				left.add(i);
			}else{
				right.add(i);
			}
			itr.remove();
}
		left = randomQuick(left);
		right= randomQuick(right);
		temp.addAll(left);
		temp.add(pivot);
		temp.addAll(right);
		return temp;
}		//referenced class code
		//return new ArrayList<>(list);
	}

