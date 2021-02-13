package dev.pprotsiv.travel.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;

import java.io.IOException;

public class HotelDeserializer extends JsonDeserializer<Hotel> {
    @Override
    public Hotel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Address address = new Address(node.get("country").textValue(),
                node.get("city").textValue(),
                node.get("street").textValue(),
                node.get("houseNumber").textValue());
        Hotel hotel = new Hotel(node.get("name").textValue(),
                address);
        JsonNode id = node.get("id");
        if (id!=null){
            hotel.setId(id.asLong());
        }
        return hotel;
    }
}

