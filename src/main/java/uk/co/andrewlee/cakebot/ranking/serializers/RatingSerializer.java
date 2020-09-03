package uk.co.andrewlee.cakebot.ranking.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import de.gesundkrank.jskills.Rating;

import java.lang.reflect.Type;

public class RatingSerializer implements JsonSerializer<Rating>, JsonDeserializer<Rating> {

  private static final String MEAN_PROPERTY = "mean";
  private static final String STANDARD_DEVIATION = "std";
  private static final String MULTIPLIER = "multiplier";

  @Override
  public JsonElement serialize(Rating rating, Type type,
      JsonSerializationContext jsonSerializationContext) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(MEAN_PROPERTY, rating.getMean());
    jsonObject.addProperty(STANDARD_DEVIATION, rating.getStandardDeviation());
    jsonObject.addProperty(MULTIPLIER, rating.getConservativeStandardDeviationMultiplier());
    return jsonObject;
  }

  @Override
  public Rating deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    return new Rating(jsonObject.get(MEAN_PROPERTY).getAsDouble(),
        jsonObject.get(STANDARD_DEVIATION).getAsDouble(),
        jsonObject.get(MULTIPLIER).getAsDouble());
  }
}
