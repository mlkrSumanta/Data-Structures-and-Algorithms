import java.util.*;
import java.lang.*;
import java.math.*;

class DivThree {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			
			String str = ""; 
			int res = -1;
			int even = 0;
			int odd = 0;

			str = input.next();

			/*for (int i = 1; i < str.length(); i = 2*i + 1) {
				if (str.charAt(i) == '1') {odd++;}
			}
			for (int i = 0; i < str.length(); i = 2*i + 2) {
				if (str.charAt(i) == '1') {even++;}
			}*/
			BigInteger num = new BigInteger(str, 2);
			BigInteger three = new BigInteger("11", 2);

			// int result = Math.abs(odd - even);
			if (num.mod(three) == BigInteger.ZERO) {res = 1;} 
			else {res = 0;}

			resultArr.add(res);	
		}

		for (int i = 0; i < resultArr.size(); i++) {System.out.println(resultArr.get(i));}
	}
}