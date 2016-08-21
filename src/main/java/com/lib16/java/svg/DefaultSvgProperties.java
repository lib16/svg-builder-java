package com.lib16.java.svg;

import com.lib16.java.utils.NumberFormatWrapper;
import com.lib16.java.xml.XmlProperties;

public class DefaultSvgProperties extends XmlProperties implements SvgProperties
{
	private NumberFormatWrapper formatWrapper = new NumberFormatWrapper(4);
	private NumberFormatWrapper degreeFormatWrapper = new NumberFormatWrapper(2);

	@Override
	public String getMimeType()
	{
		return "image/svg+xml";
	}

	@Override
	public String getFilenameExtension()
	{
		return "svg";
	}

	@Override
	public NumberFormatWrapper getFormatWrapper()
	{
		return formatWrapper;
	}

	@Override
	public NumberFormatWrapper getDegreesFormatWrapper()
	{
		return degreeFormatWrapper;
	}
}
