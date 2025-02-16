package comA.exception;

public class InvalidAccNumberException extends Exception {

private String msg;
	
	public InvalidAccNumberException (String msg) {
		this.msg=msg;
	}
	
  public String toString() {
	  return "Problem is ..."+msg ;
	  }
	
}
