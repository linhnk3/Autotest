package rest;

import com.google.gson.*;
import exceptions.JsonElementNotFoundException;


/**
 * [] -> tương ứng là JsonArray
 * {} -> tương ứng là JsonObject
 * <p>
 * - Nếu JsonArray chỉ chứa giá trị (value) thì JsonArray.addJsonElement(value)
 * - Nếu JsonArray chứa 1 hay nhiều {} - JsonObject thì
 * + Tạo JsonObject trước
 * + JsonArray.addJsonElement(JsonObject)
 * - Nếu JsonObject chỉ bao gồm 1 hoặc nhiều cặp "key, value" thì JsonObject.addProperty(key, value)
 * - Nếu JsonObject hay JsonArray có parentKey thì
 * + Tạo JsonObject và JsonArray trước
 * + Tạo JsonObject cha
 * + JsonObject cha.addJsonElement(parentKey, JsonObject/JsonArray)
 */

public class RestBody {

    private JsonObject jsonObject;

    //object {}
    // array []


    public RestBody() {
        jsonObject = new JsonObject();
    }

    public String print() {
        return new Gson().toJson(jsonObject);
    }


    public String prettyPrint() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);
    }

    /**
     * This method is for adding/updating property into root JsonObject
     *
     * @param key key is at level 1
     *                  If JsonObject is found by key, JsonObject will replace its value by new value
     *                  If JsonObject is NOT found by key, JsonObject will be created with value
     * @param value     value could be Number, String, Boolean, Character
     */
    public void add(String key, Object value) throws JsonElementNotFoundException {
        add(jsonObject, key, value);
    }




    /**
     * This method is for adding/updating a JsonArray of values into root JsonObject
     * <p>
     * If JsonArray (child) is found by key:
     * - JsonArray (child) will add value as a JsonPrimitive
     * - JsonArray (child) will be added into root JsonObject
     * If JsonArray (child) is NOT found by key
     * - JsonArray (child) will be created and then add value
     * - JsonArray (child) will be added into root JsonObject
     *
     * @param key key is at level 1
     * @param value
     * @throws JsonElementNotFoundException
     */
    public void addArrayValue(String key, Object value) throws JsonElementNotFoundException {
        addArrayValue(jsonObject, key, value);
    }




    /**
     * This method is for adding/updating JsonObject with parentKey into root JsonObject
     * <p>
     * If JsonObject (child) is found by parentKey
     * - JsonObject (child) will add property with key and value
     * - Then JsonObject (child) will be added into root JsonObject
     * If JsonObject (child) is NOT found by parentKey
     * - JsonObject (child) will be created
     * - JsonObject (child) will add property with key and value
     * - Then JsonObject (child) will be added into root JsonObject
     *
     * @param parentKey parentKey is at level 1
     * @param key
     * @param value
     * @throws JsonElementNotFoundException
     */
    public void addMap(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        addMap(jsonObject, parentKey, key, value);
    }


    public void deleteArrayMap(String key, Integer index) {
        deleteArrayMap(jsonObject, key, index);
    }

    public void delete(String key) {
        delete(jsonObject, key);
    }

    public void deleteArrayValue(String key, Integer index) {
        deleteArrayValue(jsonObject, key, index);
    }


    private void deleteArrayValue(JsonObject jsonObject, String parentKey, Integer index) {
        JsonElement je = jsonObject.get(parentKey);
        if (je != null && je.isJsonArray()) {
            je.getAsJsonArray().remove(index);
        }
    }

    private void delete(JsonObject jsonObject, String key) {
        if (jsonObject.get(key) != null) {
            jsonObject.remove(key);
        }
    }


    private void deleteArrayMap(JsonObject jsonObject, String parentKey, Integer index) {
        JsonElement je = jsonObject.get(parentKey);
        if (je != null && je.isJsonArray()) {
            JsonElement jeChild = je.getAsJsonArray().get(index);
            if (jeChild != null)
                jeChild.getAsJsonArray().remove(index);
        }
    }

    private JsonObject addProperty(JsonObject jo, String key, Object value) {
        if (value instanceof Number) {
            jo.addProperty(key, (Number) value);
        } else if (value instanceof String) {
            jo.addProperty(key, (String) value);
        } else if (value instanceof Character) {
            jo.addProperty(key, (Character) value);
        } else if (value instanceof Boolean) {
            jo.addProperty(key, (Boolean) value);
        } else if (value instanceof JsonElement) {
            jo.add(key, (JsonElement) value);
        }

        return jo;
    }

    private JsonObject addJsonElement(JsonObject jo, String key, JsonElement value) {
        jo.add(key, value);
        return jo;
    }


    /*  @param ja :  thể hiện là 1 json Array
     *  @param value :  thể hiện là giá trị của Array chứa nguyên object data
     *  ==> Đẩy dữ liệu: addPrimitive là true/false vô đúng vị trí đó
     * */
    private void addPrimitive(JsonArray ja, Object value) {
        if (value instanceof Number) {
            ja.add(new JsonPrimitive((Number) value));
        } else if (value instanceof String) {
            ja.add(new JsonPrimitive((String) value));
        } else if (value instanceof Character) {
            ja.add(new JsonPrimitive((Character) value));
        } else if (value instanceof Boolean) {
            ja.add(new JsonPrimitive((Boolean) value));
        }
    }


    private JsonElement add(JsonObject jsonObject, String key, Object value) throws JsonElementNotFoundException {
        if (key.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\" or \\[ or \\]");
        }
        return addProperty(jsonObject, key, value);
    }



    private void addMap(JsonObject jsonObject, String parentKey, String key, Object value) throws JsonElementNotFoundException {
        if (parentKey.contains(".") || key.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\"");
        }
        JsonObject jo = jsonObject.getAsJsonObject(parentKey);
        if (jo == null)
            jo = new JsonObject();

        addProperty(jo, key, value);
        addJsonElement(jsonObject, parentKey, jo);
    }

    private void addArrayValue(JsonObject jsonObject, String parentKey, Object value) throws JsonElementNotFoundException {
        if (parentKey.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\" ");
        }
        JsonArray ja = jsonObject.getAsJsonArray(parentKey);
        if (ja == null) {
            ja = new JsonArray();
        }

        addPrimitive(ja, value);
        addJsonElement(jsonObject, parentKey, ja);
    }




}
