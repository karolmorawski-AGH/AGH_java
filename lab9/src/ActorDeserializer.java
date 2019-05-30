import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorDeserializer extends StdDeserializer<Actor> {
    public ActorDeserializer() {
        this(null);
    }

    public ActorDeserializer(Class<Actor> vc) {
        super(vc);
    }

    @Override
    public Actor deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        Actor result=new Actor();
        String id="",name="";

        JsonNode node = codec.readTree(jp);

        JsonNode idNode = node.get("id");
        id = idNode.asText();

        JsonNode nameNode = node.get("name");
        name = nameNode.asText();

        result.setId(id);
        result.setName(name);

        return result;
    }
}
