import java.util.*;

class LongEvSubs {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			String arr = input.next();
			int start = 0, end = arr.length()-1;
			int result = 0;
			if (end%2 == 0) {
				for (int i = end-1; i >= 0; i = i-2) {
					start = 0;
					end = start+i;
					while (end < arr.length()) {
						int mid = (start+end)/2;
						int sumFirst = sum(start, mid, arr);
						int sumSecond = sum(mid+1, end, arr);
						if (sumFirst == sumSecond) {result = i+1; i = -1; break;}
						start++; end++;
					}
				}
			} else {
				for (int i = end; i >= 0; i = i-2) {
					start = 0;
					end = start+i;
					while (end < arr.length()) {
						int mid = (start+end)/2;
						int sumFirst = sum(start, mid, arr);
						int sumSecond = sum(mid+1, end, arr);
						if (sumFirst == sumSecond) {result = i+1; i = -1; break;}
						start++; end++;
					}
				}
			}
			resultArr.add(result);	
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}

	public static int sum(int start, int end, String arr) {
		int sumR = 0;
		for (int i = start; i <= end; i++) {sumR += Character.getNumericValue(arr.charAt(i));}
		return sumR;
	}
}