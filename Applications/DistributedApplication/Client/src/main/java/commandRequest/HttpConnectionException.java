package commandRequest;

@SuppressWarnings("serial")
public class HttpConnectionException extends Exception {

		public HttpConnectionException(){
		}

		public HttpConnectionException(String message){
			super(message);
		}
}
