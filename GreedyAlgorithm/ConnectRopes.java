import java.util.*;

class ConnectRopes {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int result = 0;
			int size = input.nextInt();			
			PriorityQueue<Integer> heap = new PriorityQueue<>();
			for (int i = 0; i < size; i++) {heap.add(input.nextInt());}
			while (heap.size() > 1) {
				int num1 = heap.remove();
				int num2 = heap.remove();
				int resNum = num1+num2;
				result += resNum;
				heap.add(resNum);
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}