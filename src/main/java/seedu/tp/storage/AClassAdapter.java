package seedu.tp.storage;

// Credit to: https://stackoverflow.com/questions/16872492/gson-and-abstract-superclasses-deserialization-issue

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AClassAdapter<A> implements JsonSerializer<A>, JsonDeserializer<A> {
    @Override
    public JsonElement serialize(A src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }

    @Override
    public A deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            String fullName = typeOfT.getTypeName();
            String packageText = fullName.substring(0, fullName.lastIndexOf(".") + 1);

            return context.deserialize(element, Class.forName(packageText + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}