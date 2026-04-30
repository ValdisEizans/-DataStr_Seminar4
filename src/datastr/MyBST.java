package datastr;

public class MyBST<Ttype>{
	private MyNode<Ttype> rootNode = null;
	private int howManyElements = 0;
	
	//geteri, prieks blokiem nevajag
	public int getHowManyElements() {
		return howManyElements;
	}
	
	//seteri - nav nepieciesami
	
	//konstruktori - nevajag nevienu, jo default jau nak no Object klases
	
	private boolean isEmpty() {
		return (howManyElements == 0); 
	}
	
	private boolean isFull() {
		try {
			new MyNode<Character>('A');//megina RAM rezervet vietu
			return false;
		}
		catch(OutOfMemoryError e){
			return true;
		}
	}
	
	public void add(Ttype element) throws Exception{
		if(isFull()) {
			throw new Exception("Koks ir pilns, nevar pievienot elementu!");
		}
		addHelper(rootNode, element);
		howManyElements++;
		
	}
	
	private void addHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp != null) {
			if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {
				addHelper(nodeTemp.getRightChildNode(), element);
				
			}
			else {
				addHelper(nodeTemp.getLeftChildNode(), element);

			}
			
			
			
			
			
			
		}
	}
	
	

}
