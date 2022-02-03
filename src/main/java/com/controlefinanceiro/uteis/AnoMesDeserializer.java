package com.controlefinanceiro.uteis;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class AnoMesDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p,
			DeserializationContext ctxt) throws IOException {

		String value = p.getValueAsString();
				
		if (value.isEmpty() == false && value.length()==7) {
			value = value+"-01";
			return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
		} else if(value.isEmpty() == false && value.length()==10) {
			return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
		}
		else {
			throw ctxt.weirdStringException(value, 
					LocalDate.class, "Value doesn't start with \"dob:\"");
		}
	}
}
