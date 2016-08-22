package com.lib16.java.svg;

import java.util.LinkedHashMap;

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
	public String getXmlNamespace()
	{
		return "http://www.w3.org/2000/svg";
	}

	@Override
	public LinkedHashMap<String, String> getMoreXmlNamespaces()
	{
		LinkedHashMap<String, String> namespaces = new LinkedHashMap<>();
		namespaces.put("xlink", "http://www.w3.org/1999/xlink");
		return namespaces;
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
