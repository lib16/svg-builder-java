package com.lib16.java.svg;

import com.lib16.java.utils.NumberFormatter;
import com.lib16.java.xml.LanguageProperties;

public interface SvgProperties extends LanguageProperties
{
	public String getSvgVersion();

	public NumberFormatter getFormatter();

	public NumberFormatter getDegreesFormatter();
}
