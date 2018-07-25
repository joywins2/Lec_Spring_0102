package exem.java12.interfaces;

//...reference from : https://m.post.naver.com/viewer/postView.nhn?volumeNo=6223377&memberNo=30800755&navigationType=push

public abstract class Calculator {
	
	int result;
	
	public int add() {
		result = result + 1;
		return result;
	}
	
	public abstract int minus();
	
}
