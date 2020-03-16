package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Principal {
	public static void main(String[] args) {
		System.out.println("Test");
		
		
		//int[] arr = {10,20,20,10,10,30,50,10,20};
		
		//int result = sockMerchant(arr.length, arr);
		
		//System.out.println(result);
		
		
		//int[] jumpingClouds = {0, 0, 0, 1, 0, 0};
		
		//int jumpingOnClouds = jumpingOnClouds(jumpingClouds);
		//System.out.println(jumpingOnClouds);
		
		
		//System.out.println(repeatedString("ababa", 3));
		
		/*
		int [][] arr = new int [6][6];
		
		int [] a = {1, 1, 1, 0, 0, 0};
		int [] b = {0, 1, 0, 0, 0, 0};
		int [] c = {1, 1, 1, 0, 0, 0};
		int [] d = {0, 0, 2, 4, 4, 0};
		int [] e = {0, 0, 0, 2, 0, 0};
		int [] f = {0, 0, 1, 2, 4, 0};
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		arr[3] = d;
		arr[4] = e;
		arr[5] = f;
		
		
		System.out.println(hourglassSum(arr));
		*/
	}
	
	
	static int hourglassSum(int[][] arr) {
		
		int rows = arr[0].length;
		int columns = arr.length;
		
		System.out.println(rows);
		
		System.out.println(columns);
		
		int minColumns = 3;
		int minRows = 3;
		
		
		int topRow = 0;
		int middleRow = 1;
		int bottomRow = 2;
		
		int leftColumn = 0;
		int middleColumn = 1;
		int rightColumn = 2;
		
		
		shiftIsValid(
				rows, 
				columns, 
				topRow, 
				bottomRow, 
				leftColumn, 
				rightColumn);
		
		
		List<Integer> sums = new ArrayList<Integer>();
		
		while(shiftIsValid(rows, columns, topRow, bottomRow, leftColumn, rightColumn))
		{
			
			if (leftColumn == 2 && topRow == 3)
			{
				System.out.println("come here...");
			}
			
			int sum = getSum(arr, topRow, middleRow, bottomRow, leftColumn, middleColumn, rightColumn);
			
			sums.add(sum);
			
			boolean canMoveToRight = rightColumn != columns-1;
			boolean bottomReached = bottomRow == rows-1;
			boolean rightReached = rightColumn == columns-1;
			boolean canMoveToDown = bottomRow != rows-1;
			
			if(bottomReached && rightReached)
				break;
			if (canMoveToRight && bottomReached)
			{
				rightColumn++;
				middleColumn++;
				leftColumn++;
			} 
			else if (rightReached && canMoveToDown)
			{
				rightColumn = 2;
				middleColumn = 1;
				leftColumn = 0;
				
				topRow++;
				middleRow++;
				bottomRow++;
			} else if (canMoveToRight) {
				rightColumn++;
				middleColumn++;
				leftColumn++;
			}
			
		}
		
		Collections.sort(sums);
		
		sums.forEach(System.out::println);
		
		return sums.get(sums.size()-1);
    }


	private static int getSum(int[][] arr, int topRow, int middleRow, int bottomRow, int leftColumn, int middleColumn,
			int rightColumn) {
		int sum = 0;
		sum = sum + arr[topRow][leftColumn];
		sum = sum + arr[topRow][middleColumn];
		sum = sum + arr[topRow][rightColumn];
		
		sum = sum + arr[middleRow][middleColumn];
		
		sum = sum + arr[bottomRow][leftColumn];
		sum = sum + arr[bottomRow][middleColumn];
		sum = sum + arr[bottomRow][rightColumn];
		return sum;
	}


	private static boolean shiftIsValid(int rows, int columns, int topRow, int bottomRow, int leftColumn,
			int rightColumn) {
		boolean shiftValid = false;
		if (topRow >= 0 &&
				bottomRow < rows &&
				leftColumn >= 0 &&
				rightColumn < columns)
		{
			shiftValid = true;
		}
		return shiftValid;
	}
	
	static long repeatedString(String s, long n) {
       
		if (s.equals("a"))
			return n;
		
		long aCount = 0 ;
		for (char c : s.toCharArray())
			if (c == 'a')
				aCount++;	
		
		if (n > s.length())
		{
			
			long mod = getMod(s.length(), n);
			System.out.println(mod);
			
			if (mod == 0)
			{
				long division = n / s.length();
				return aCount * division;
			}
			else
			{
				int length = s.length();
				long tempN = n;
				while(getMod(length, tempN) != 0)
				{
					tempN--;
				}
				
				long division = tempN / s.length();
				long total = division * aCount;
				
				for (char c : s.substring(0, new Integer(String.valueOf(n-tempN))).toCharArray())
					if (c == 'a')
						total++;
				return total;
			}
		}
		else if (n < s.length()) {
			long total = 0 ;
			for (char c : s.substring(0, new Integer(String.valueOf(n))).toCharArray())
				if (c == 'a')
					total++;
			return total;
		}
		else
			return aCount;
    }


	private static long getMod(int l, long n) {
		long mod = n % l;
		return mod;
	}
	
	static int sockMerchant(int n, int[] ar) {
		
        int result = 0;
        
        Map<Integer, Integer> map = new HashMap();
        
        for(int i : ar)
        {
            if (map.containsKey(i))
            {
                int count = map.get(i);
                count++;
                map.put(i, count);
            }
            else
                map.put(i,1);
        }

        Set<Integer> keys = map.keySet();
        for (Integer key : keys)
        {
        	int totalByKey = map.get(key);
        	int mod = totalByKey % 2;
        	
            if (mod == 0)
                result = result + (totalByKey / 2);
            else
                result = result + (totalByKey-1) / 2;
        }
        return result;
    }
	
	
	static int countingValleys(int n, String s) {
		
		char[] charArray = s.toCharArray();
		
		int valleys = 0;
		int mountains = 0;
		char UP = 'U';
		char DOWN = 'D';
		
		int altitude = 0;
		
		for (char c : charArray) {
			
			if (c == UP)
			{
				altitude++;
				if (altitude == 0)
					valleys++;
			}
			else if (c == DOWN)
			{
				altitude--;
				if (altitude == 0)
					mountains++;
			}
		}
		
        
        return valleys;
        
    }
	
		
	static int jumpingOnClouds(int[] c) {
			int jumps = 0;
			
			List<Integer> evict = new ArrayList<Integer>();
			
			int lenf = c.length;
			int i = 0;
			
			
			while (i < lenf-1) 
			{	
			  if (i+2 < lenf) { 
				  jumps++; 
				  if (c[i+2] == 0) 
					  i = i+2; 
				  else 
					  i++;
			  }
			  else
			  {
				  jumps++;
				  i++;
			  }
			}
			return jumps;

		}
}
