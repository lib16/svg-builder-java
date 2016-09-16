package com.lib16.java.svg;

import java.util.LinkedHashMap;

import com.lib16.java.utils.NumberFormatter;
import com.lib16.java.utils.enums.ImageType;
import com.lib16.java.xml.XmlProperties;

public class DefaultSvgProperties extends XmlProperties implements SvgProperties
{
	@Override
	public String getMimeType()
	{
		return ImageType.SVG.toString();
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
	public String getSvgVersion()
	{
		return null;
	}

	@Override
	public NumberFormatter getFormatter()
	{
		return NumberFormatter.DEFAULT_FORMATTER;
	}

	@Override
	public NumberFormatter getDegreesFormatter()
	{
		return NumberFormatter.DEFAULT_DEGREE_FORMATTER;
	}
}
