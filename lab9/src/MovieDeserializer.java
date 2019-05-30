import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.json.JSONException;

import java.io.IOException;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDeserializer extends StdDeserializer<Movie> {
	public MovieDeserializer() {
		this(null);
	}

	public MovieDeserializer(Class<Movie> vc) {
		super(vc);
	}


	@Override
	public Movie deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();

		Movie result = null;
		try {
			result = new Movie();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String id;
		String title;

		JsonNode node = codec.readTree(jp);

		JsonNode idNode = node.get("id");
		id = idNode.asText();

		JsonNode titleNode = node.get("title");
		title = titleNode.asText();

		result.setId(id);
		result.setTitle(title);

		return result;
	}
}
