package br.ufc.mdcc.infoshop.util;

import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

public class CustomFeedbackListToStringConverter implements Converter<CustomFeedbackList, String> {

	@Override
	public String convert(CustomFeedbackList source) {
		return source.customFeedbacks.stream().map(Object::toString).collect(Collectors.joining(", "));
	}
}