package com.lib16.java.svg;

import com.lib16.java.utils.NumberFormatWrapper;
import com.lib16.java.xml.LanguageProperties;

public interface SvgProperties extends LanguageProperties
{
	public String getSvgVersion();

	public NumberFormatWrapper getFormatWrapper();

	public NumberFormatWrapper getDegreesFormatWrapper();
}
