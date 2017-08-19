import java.util.*;

class KadanesAlgo {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			
			int maxValue = 0, resultValue = 0;
			int size = input.nextInt();
			int[] array = new int[size];
			
			for (int i = 0; i < size; i++) {

				array[i] = input.nextInt();
				if (i == 0) {maxValue = array[0]; resultValue = maxValue;}
				else if (maxValue < 0) {
					
					maxValue = array[i];
					if (maxValue > resultValue) {resultValue = maxValue;}					
				} else if (maxValue >= 0) {

					maxValue = maxValue + array[i];
					if (maxValue > resultValue) {resultValue = maxValue;}					
				}
 			}
 			resultArr.add(resultValue);
 		}
 		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}