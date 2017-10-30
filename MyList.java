package com.uh.cosc4353;
import org.apache.commons.cli.*;
import java.lang.*;

class MyList
{
	public static int binSearch(Comparable[] aList, Comparable key)
	{	
		int N = aList.length;
		int L = 0;
		int R = N-1;
		
		while(L <= R){
			int M = L + (R-L)/2;
			
			if(aList[M].compareTo(key) == 0){
				return M;
			}
			if(aList[M].compareTo(key) < 0){
				L = M+1;
			} else {
				R = M-1;
			}
		}
		
		return -1;
	}
	
	public static int[] SelectionSort(int[] aList)
	{
         for (int i = 0; i < aList.length - 1; i++){
            int ptr = i;
			
            for (int j = i + 1; j < aList.length; j++){
                if (aList[j] < aList[ptr]) 
                    ptr = j;
			}
			
            int smallerNum = aList[ptr];  
            aList[ptr] = aList[i];
            aList[i] = smallerNum;
        }
        return aList;
    }
	
	public static void main(String[] args) throws Exception
	{
		Options options = new Options();
		
		Option typeOp = Option.builder("type")
					.longOpt("type")
					.hasArg()
					.required()
					.build();
		options.addOption(typeOp);
		
		Option keyOp = Option.builder("key")
				.longOpt("key")
				.hasArg()
				.required()
				.build();
		options.addOption(keyOp);
		
		Option listOp = Option.builder("list")
					.longOpt("list")
					.hasArgs()
					.valueSeparator(' ')
					.required()
					.build();
		options.addOption(listOp);
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		
		try{
			cmd = parser.parse(options, args);
		} catch (ParseException ex){
			System.out.println(ex.getMessage());
			System.exit(1);
			return;
		}
		
		String type = cmd.getOptionValue("type");
		String key = cmd.getOptionValue("key");
		String[] aList = cmd.getOptionValues("list");
		
		if(type.equals("i")) {
			try {
				int[] bList = new int[aList.length];
				for(int i = 0; i < aList.length; i++){
					bList[i] = Integer.parseInt(aList[i]);
				}
				bList = SelectionSort(bList);
				for(int i = 0; i < aList.length; i++){
					aList[i] = Integer.toString(bList[i]);
				}
			} catch (NumberFormatException ex) {
				System.out.println("INVALID TYPE");
				System.exit(1);
			}
		}
		
		int output = binSearch(aList, key);
		if(output != -1){
			System.out.println("1");
		}else{
			System.out.println("0");
		}
	}
}
		