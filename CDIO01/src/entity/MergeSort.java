package entity;

public class MergeSort {
	
	private int[] numbers, helper;
	private int length;
	
	public void sort(int[] A) {
		
		this.numbers = A;
		length = A.length;
		this.helper = new int[length];
		mergeSort(0, length - 1);
		
	}
	
	public void mergeSort(int low, int high) {
		
		if (low < high) {
			int middle = low + (high - low) / 2;
			
			mergeSort(low, middle);
			
			mergeSort
		}
		
	}
	
	public void merge() {
		
	}
	
}
