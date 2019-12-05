public class Pair<T> {
	T leftNum;
	T rightNum;
	
	Pair(T lNum, T rNum) {
		leftNum = lNum;
		rightNum = rNum;
	}
	public String toString() {
		return leftNum + "," + rightNum;
	}
	public T left() { return leftNum; }
	public T right() {return rightNum; }
}