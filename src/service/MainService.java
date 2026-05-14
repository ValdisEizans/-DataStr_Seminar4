package service;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) throws Exception {
		MyBST<Integer> bstKoks = new MyBST<Integer>();
		try {
			bstKoks.add(10);
			bstKoks.add(6);
			bstKoks.add(23);
			bstKoks.add(8);
			bstKoks.add(7);
			System.out.println("----------------------------------------------------------------------------");
			bstKoks.print();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
