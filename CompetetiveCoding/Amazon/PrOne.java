import java.util.*;
import java.util.PriorityQueue;

class PrOne {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<int[]> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			
			int[] line = new int[2];
			for (int i = 0; i < 2; i++) {line[i] = input.nextInt();}
			int[] a = new int[line[0]];
			int size = line[0];
			int res = line[1];
			for (int i = 0; i < size; i++) {
				a[i] = input.nextInt();
			}

			class Solution {

				int res;
				PriorityQueue<Integer> BTArray;
				int[] result;

				public Solution(int res) {

					this.res = res;
					BTArray = new PriorityQueue<>(res);
				}

				public void add(int num) {

					if (BTArray.size()+1 > res) {

						BTArray.poll();
						BTArray.add(num);
						return;
					}
					BTArray.add(num);
				}

				public void getArray() {

					result = new int[res];
					for (int i = res-1; i >= 0; i--) {
						if (BTArray.peek() != null) {result[i] = BTArray.poll();}
					}
				}
			}

			Solution sol = new Solution(res);
			sol.add(a[0]);
			for (int i = 1; i < size; i++) {
				if (a[i] > sol.BTArray.peek() || sol.BTArray.size()+1 <= res) {
					// System.out.println(sol.BTArray.peek() + ", " + a[i]);
					sol.add(a[i]);
				}
			}

			sol.getArray();
			resultArr.add(sol.result);	
		}
		for (int i = 0; i < resultArr.size(); i++) {
			int[] result = resultArr.get(i);
			for (int j = 0; j < result.length; j++) {
				System.out.print(result[j] + " ");
			}
			System.out.println();
		}
	}
}