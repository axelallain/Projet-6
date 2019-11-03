package fr.axelallain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.axelallain.entity.Spot;
import fr.axelallain.service.SpotService;

@Component
public class StringToSpot implements Converter<String, Spot>{
	
	@Autowired
	private SpotService spotService;

	@Override
	public Spot convert(String arg0) {
		Long id = Long.parseLong(arg0);
		return spotService.findSpotById(id);
	}

}
