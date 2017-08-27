import java.util.*;

class SearchInRotatedArray {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[] array = new int[size];
			int result = -1;
			int index = size-1;
			for (int i = 0; i < size; i++) {
				array[i] = input.nextInt();
				if (i > 0 && array[i] < array[i-1]) {index = i-1;}
			}
			int search = input.nextInt();
			if (search >= array[0]) {
				if (search == array[0]) {result = 0;}
				else if (search == array[index]) {result = index;}
				else {result = bsearch(0, index, search, array);}
			} else {
				if (search == array[index+1]) {result = index+1;}
				else if (search == array[size-1]) {result = size-1;}
				else {result = bsearch(index+1, size-1, search, array);}
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}

	public static int bsearch(int start, int end, int search, int[] array) {
		if (start >= end) {return -1;}
		int mid = (start+end)/2;
		if (search == array[mid]) {return mid;}
		if (search < array[mid]) {return bsearch(start, mid, search, array);}
		if (search > array[mid]) {return bsearch(mid+1, end, search, array);}
		return -1;
	}
}