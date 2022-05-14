package com.robb.banking.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robb.banking.models.Customer_info;
import com.robb.banking.util.collections.LinkedList;

import java.io.IOException;

public class LinkedListSerializerCustomer_info extends StdSerializer<LinkedList> {

    public LinkedListSerializerCustomer_info() { this(null); }

    public LinkedListSerializerCustomer_info(Class<LinkedList> t) { super(t); }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            Customer_info customer_info = (Customer_info) linkedList.get(i);
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
