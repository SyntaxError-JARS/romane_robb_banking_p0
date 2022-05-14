package com.robb.banking.util.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robb.banking.models.Customer_info;

import java.io.IOException;

public class ArrayListSerializer extends StdSerializer<ArrayList> {

    public ArrayListSerializer() { this(null); }

    public ArrayListSerializer(Class<ArrayList> t) { super(t); }

    @Override
    public void serialize(ArrayList arrayList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < arrayList.size(); i++) {
            Customer_info customer_info = (Customer_info) arrayList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("first_name", customer_info.getFirst_name());
            jsonGenerator.writeStringField("last_name", customer_info.getLast_name());
            jsonGenerator.writeStringField("email_address", customer_info.getEmail_address());
            jsonGenerator.writeStringField("user_password", customer_info.getUserpassword());
            jsonGenerator.writeStringField("date_of_birth", customer_info.getDate_of_birth());
            jsonGenerator.writeEndObject();
        }
    }
}
