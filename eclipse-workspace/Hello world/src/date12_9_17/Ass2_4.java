package date12_9_17;

public class Ass2_4 {
	public static void main(String a[]){
        int febCount = 13;
        int[] feb = new int[febCount];
        feb[0] = 0;
        feb[1] = 1;
        for(int i=2; i < febCount; i++){
            feb[i] = feb[i-1] + feb[i-2];
        }
        for(int i=1; i< febCount; i++){
                System.out.print(feb[i] + " ");
        }
   }
}
