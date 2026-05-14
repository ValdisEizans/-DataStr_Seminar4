package service;

import datastr.MyBST;
import model.Patient;

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
		
		MyBST<Patient> bstPatients = new MyBST<Patient>();
		try {
			System.out.println("----------------------------DARBIBAS AR PACIENTIEM------------------------------------------------");
			bstPatients.add(new Patient("Janis","Berzins",4));
			bstPatients.add(new Patient("Liga","Eglite",2));
			bstPatients.add(new Patient("Baiba","Kalnina",3));
			bstPatients.add(new Patient("Juris","Nejaukais",5));
			bstPatients.print();
			System.out.println("----------------------------------------------------------------------------");
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
