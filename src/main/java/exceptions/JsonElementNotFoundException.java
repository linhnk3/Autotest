package exceptions;

public class JsonElementNotFoundException extends Exception {
    //Trace message lúc văng exception lỗi
    public JsonElementNotFoundException(String message){
        super(message);
    }
}
