package rest;

import java.util.HashMap;

public class RestParams {
    private HashMap<String, Object> params;


    //created constructor
    public RestParams() {
        this.params = new HashMap<>();
    }


    //add param
    public void addParam(String key, Object value){
        this.params.put(key, value);
    }

    //remove param
    public void removeParam(String key, Object value){
        this.params.remove(key, value);
    }


    // get all params add
    public HashMap<String, Object> getParams(){
        return this.params;
    }
}
