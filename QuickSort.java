import java.util.Arrays;

public class QuickSort {

	final int[] sortArr;

	public QuickSort(int[] inputArr){
		sortArr=inputArr;
	}
	
	public void sort(){
		sort(0,sortArr.length-1);		
	}
	public void sort(int start, int end){
	
		if(start>=end){
		return;
		}
		
		int left=start+1;
		int right=end;
		int pivot=sortArr[start];
		
		while(left<=right){
			while(left<=right && sortArr[left]<=pivot ){
				left++;
			}
			while(left<=right && sortArr[right]>=pivot){
				right--;
			}
			if(left<right){
				swap(left,right);
			}
			else {
				swap(start,left-1);
			}
		}
		
		sort(start,left-2);
		sort(left,end);
	}
	public void swap(int left, int right){
				int tmp=sortArr[left];
				sortArr[left]=sortArr[right];
				sortArr[right]=tmp;
	
	}
	
	public static void main(String[] args) {
			QuickSort temp=new QuickSort(new int[]{5,2,3,5,6,7});  
			temp.sort();
			
			Arrays.stream(temp.sortArr).forEach(System.out::println);
	//한 개의 피벗
	//두 개의 포인터
	
	//포인터는 각각 피벗이 아닌 배열의 맨왼쪽, 피벗이 아닌 배열의 맨 오른쪽에 위치한다.
	//맨왼쪽에 있는 포인터를 left, 맨 오른쪽에 있는 포인터를 right라고 했을 때
	//left<피벗이면, ++ right>피벗이면 --해준다. 
	
	//이렇게 해서 right<=left 가 되면  left의 왼쪽은 피벗보다 다 작을테니 left-1을 피벗과 교체해준다...
	
	//피벗을 기준으로 왼쪽 배열, 오른쪽 배열을 나눠 배열 하나씩 위의 과정을 반복한다.
	//배열의 길이가 1이 되면 끝낸다.


	}

}
