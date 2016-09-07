package com.lib16.java.svg;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Path;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;
import com.lib16.java.utils.Unit;
import com.lib16.java.xml.Language;
import com.lib16.java.xml.Xml;
import com.lib16.java.xml.shared.ClassAttribute;
import com.lib16.java.xml.shared.TargetAttribute;
import com.lib16.java.xml.shared.TargetAttribute.Target;
import com.lib16.java.xml.shared.XLink;

public final class Svg implements Language
{
	private Xml xml;

	private Svg(Xml xml)
	{
		this.xml = xml;
	}

	public static Svg createSvg(SvgProperties properties,
			Number width, Unit widthUnit, Number height, Unit heightUnit)
	{
		if (properties == null) {
			properties = new DefaultSvgProperties();
		}
		Svg element = new Svg(Xml.createRoot("svg", properties));
		NumberFormatter wrapper = element.getFormatter();
		element.xml.getAttributes()
				.set("version", properties.getSvgVersion())
				.setNumber("width", width, wrapper, widthUnit)
				.setNumber("height", height, wrapper, heightUnit)
				.setNull("viewBox", "preserveAspectRatio");
		return element;
	}

	public static Svg createSvg(SvgProperties properties)
	{
		return createSvg(properties, null, null, null, null);
	}

	public static Svg createSvg(Number width, Unit widthUnit, Number height, Unit heightUnit)
	{
		return createSvg(null, width, widthUnit, height, heightUnit);
	}

	public static Svg createSvg()
	{
		return createSvg(null);
	}

	public static Svg createSub(SvgProperties properties)
	{
		if (properties == null) {
			properties = new DefaultSvgProperties();
		}
		return new Svg(Xml.createSub(properties));
	}

	public static Svg createSub()
	{
		return createSub(null);
	}

	public Svg rect(Point corner, Number width, Number height, Number rx, Number ry)
	{
		Svg element = new Svg(xml.append("rect"))
				.setPosition(corner)
				.setSize(width, height);
		element.xml.getAttributes()
				.setNumber("rx", rx, getFormatter())
				.setNumber("ry", ry, getFormatter());
		return element;
	}

	public Svg rect(Point corner, Number width, Number height, Number rx)
	{
		return rect(corner, width, height, rx, null);
	}

	public Svg rect(Point corner, Number width, Number height)
	{
		return rect(corner, width, height, null, null);
	}

	public Svg circle(Point center, Number r)
	{
		Svg element = new Svg(xml.append("circle"))
				.setCenter(center);
		element.xml.getAttributes()
				.setNumber("r", r, getFormatter());
		return element;
	}

	public Svg ellipse(Point center, Number rx, Number ry)
	{
		Svg element = new Svg(xml.append("ellipse"))
				.setCenter(center);
		element.xml.getAttributes()
				.setNumber("rx", rx, getFormatter())
				.setNumber("ry", ry, getFormatter());
		return element;
	}

	public Svg line(Point point1, Point point2)
	{
		return new Svg(xml.append("line"))
				.setPointAttributes(point1, "x1", "y1")
				.setPointAttributes(point2, "x2", "y2");
	}

	public Svg polyline(Point... points)
	{
		return new Svg(xml.append("polyline")).setPoints(points);
	}

	public Svg polygon(Point... points)
	{
		return new Svg(xml.append("polygon")).setPoints(points);
	}

	public Svg path(Path d)
	{
		return new Svg(xml.append("path")).setD(d);
	}

	public Svg image(String href, Point position, Number width, Number height)
	{
		Svg element = new Svg(xml.append("image"))
				.setHref(href)
				.setPosition(position)
				.setSize(width, height);
		element.xml.getAttributes().set("preserveAspectRatio", null);
		return element;
	}

	public Svg image(String href, Point position)
	{
		return image(href, position, null, null);
	}

	public Svg image(String href)
	{
		return image(href, null);
	}

	public Svg text(String content, Point position)
	{
		Svg element = new Svg(xml.append("text", content))
				.setPosition(position);
		element.xml.getAttributes()
				.set("dx", null)
				.set("dy", null)
				.set("rotate", null);
		return element;
	}

	public Svg text(String content)
	{
		return text(content, null);
	}

	public Svg text()
	{
		return text(null);
	}

	public Svg tspan(String content, Point position)
	{
		Svg element = new Svg(xml.append("tspan", content))
				.setPosition(position);
		element.xml.getAttributes()
				.set("dx", null)
				.set("dy", null)
				.set("rotate", null);
		return element;
	}

	public Svg tspan(String content)
	{
		return tspan(content, null);
	}

	public Svg tspan()
	{
		return tspan(null);
	}

	public Svg textPath(String content, String href)
	{
		return new Svg(xml.append("textPath", content)).setHref(href);
	}

	public Svg a(String href, Target target)
	{
		return new Svg(xml.append("a"))
				.setHref(href)
				.setTarget(target);
	}

	public Svg a(String href)
	{
		return a(href, null);
	}

	public Svg defs()
	{
		return new Svg(xml.append("defs"));
	}

	public Svg use(String href, Point corner, Number width, Number height)
	{
		return new Svg(xml.append("use"))
				.setHref(href)
				.setPosition(corner)
				.setSize(width, height);
	}

	public Svg use(String href, Point corner)
	{
		return use(href, corner, null, null);
	}

	public Svg g()
	{
		return new Svg(xml.append("g"));
	}

	public Svg title(String content)
	{
		return new Svg(xml.append("title", content));
	}

	public Svg desc(String content)
	{
		return new Svg(xml.append("desc", content));
	}

	public Svg clipPath(String id, Units clipPathUnits)
	{
		return new Svg(xml.append("clipPath"))
				.setId(id)
				.setUnitsAttrib("clipPathUnits", clipPathUnits);
	}

	public Svg clipPath(String id)
	{
		return clipPath(id, null);
	}

	public Svg mask(String id, Point position,
			Number width, Number height, Units maskUnits, Units maskContentUnits)
	{
		return new Svg(xml.append("mask"))
				.setId(id)
				.setPosition(position)
				.setSize(width, height)
				.setMaskUnits(maskUnits)
				.setMaskContentUnits(maskContentUnits);
	}

	public Svg mask(String id, Point position)
	{
		return mask(id, position, null, null, null, null);
	}

	public Svg mask(String id)
	{
		return mask(id, null);
	}

	public Svg style(String style)
	{
		Svg element = new Svg(xml.append("style"));
		element.xml.appendText(style);
		return element;
	}

	public Svg script(String script)
	{
		Svg element = new Svg(xml.append("script"));
		element.xml.appendText(script);
		return element;
	}

	/**
	 * Appends a {@code translate} operation to the {@code transform} attribute.
	 */
	public Svg addTranslate(double x, double y)
	{
		return setTransform("translate",
				getFormatter().format(x) + " " +
				getFormatter().format(y));
	}

	/**
	 * Appends a {@code scale} operation to the {@code transform} attribute.
	 */
	public Svg addScale(double x, double y)
	{
		return setTransform("scale",
				getFormatter().format(x) + " " +
				getFormatter().format(y));
	}

	/**
	 * Appends a {@code scale} operation to the {@code transform} attribute.
	 */
	public Svg addScale(double factor)
	{
		return setTransform("scale",
				getFormatter().format(factor));
	}

	/**
	 * Appends a {@code rotate} operation to the {@code transform} attribute.
	 */
	public Svg addRotate(Angle angle)
	{
		return setTransform("rotate",
				getDegreesFormatter().format(angle.getDegrees()));
	}

	/**
	 * Appends a {@code rotate} operation to the {@code transform} attribute.
	 */
	public Svg addRotate(Angle angle, Point center)
	{
		return setTransform("rotate",
				getDegreesFormatter().format(angle.getDegrees()) + " " +
				center.toSvg(getFormatter()));
	}

	/**
	 * Appends a {@code skewX} operation to the {@code transform} attribute.
	 */
	public Svg addSkewX(Angle angle)
	{
		return setTransform("skewX",
				getDegreesFormatter().format(angle.getDegrees()));
	}

	/**
	 * Appends a {@code skewY} operation to the {@code transform} attribute.
	 */
	public Svg addSkewY(Angle angle)
	{
		return setTransform("skewY",
				getDegreesFormatter().format(angle.getDegrees()));
	}

	/**
	 * Appends a {@code matrix} operation to the {@code transform} attribute.
	 */
	public Svg addMatrix(double a, double b, double c, double d, double e, double f)
	{
		return setTransform("matrix",
				getFormatter().format(a) + " " +
				getFormatter().format(b) + " " +
				getFormatter().format(c) + " " +
				getFormatter().format(d) + " " +
				getFormatter().format(e) + " " +
				getFormatter().format(f));
	}

	/**
	 * Sets the {@code cx} and {@code cy} attributes.
	 */
	public Svg setCenter(Point center)
	{
		return setPointAttributes(center, "cx", "cy");
	}

	/**
	 * Sets the {@code width} and {@code height} attributes.
	 */
	public Svg setSize(Number width, Number height)
	{
		return setWidth(width).setHeight(height);
	}

	public Svg setId(String id)
	{
		xml.getAttributes().set("id", id);
		return this;
	}

	public Svg setClass(String... classes)
	{
		ClassAttribute.setClass(xml, classes);
		return this;
	}

	public Svg setD(String d)
	{
		xml.getAttributes().setComplex("d", " ", false, d);
		return this;
	}

	public Svg setD(Path path)
	{
		return setD(path.toSvg(getFormatter(), getDegreesFormatter()));
	}

	public Svg setDx(Number... dx)
	{
		xml.getAttributes().setNumber("dx", " ", getFormatter(), dx);
		return this;
	}

	public Svg setDy(Number... dy)
	{
		xml.getAttributes().setNumber("dy", " ", getFormatter(), dy);
		return this;
	}

	public Svg setHeight(Number height)
	{
		xml.getAttributes().setNumber("height", height, getFormatter());
		return this;
	}

	public Svg setHref(String href)
	{
		XLink.setHref(xml, href);
		return this;
	}

	public Svg setMaskContentUnits(Units maskContentUnits)
	{
		return setUnitsAttrib("maskContentUnits", maskContentUnits);
	}

	public Svg setMaskUnits(Units maskUnits)
	{
		return setUnitsAttrib("maskUnits", maskUnits);
	}

	public Svg setPoints(Point... points)
	{
		NumberFormatter formatter = getFormatter();
		for (Point point: points) {
			xml.getAttributes().setComplex("points", " ", false, point.toSvg(formatter));
		}
		return this;
	}

	public Svg setPreserveAspectRatio(Align align, MeetOrSlice meetOrSlice, boolean defer)
	{
		String preserveAspectRatio;
		if (align != null) {
			preserveAspectRatio =
					(defer ? "defer " : "")
					+ align.toString()
					+ (meetOrSlice != null ? " " + meetOrSlice.toString() : "");
		}
		else {
			preserveAspectRatio = null;
		}
		xml.getAttributes().set("preserveAspectRatio", preserveAspectRatio);
		return this;
	}

	public Svg setPreserveAspectRatio(Align align, MeetOrSlice meetOrSlice)
	{
		return setPreserveAspectRatio(align, meetOrSlice, false);
	}

	public Svg setPreserveAspectRatio(Align align)
	{
		return setPreserveAspectRatio(align, null);
	}

	/**
	 * Sets the {@code x} and {@code y} attributes.
	 */
	public Svg setPosition(Point position)
	{
		return setPointAttributes(position, "x", "y");
	}

	public Svg setRotate(Angle... rotate)
	{
		for (Angle r: rotate) {
			xml.getAttributes().setNumber(
					"rotate", " ", getDegreesFormatter(), r.getDegrees());
		}
		return this;
	}

	public Svg setTarget(Target target)
	{
		TargetAttribute.setTarget(xml, target);
		return this;
	}

	public Svg setViewBox(Point corner, double width, double height)
	{
		String viewBox;
		if (corner != null) {
			NumberFormatter formatter = getFormatter();
			viewBox =
					formatter.format(corner.getX()) + " " +
					formatter.format(corner.getY()) + " " +
					formatter.format(width) + " " +
					formatter.format(height);
		}
		else {
			viewBox = null;
		}
		xml.getAttributes().set("viewBox", viewBox);
		return this;
	}

	public Svg setWidth(Number width)
	{
		xml.getAttributes().setNumber("width", width, getFormatter());
		return this;
	}

	@Override
	public Xml getXml()
	{
		return xml;
	}

	private Svg setTransform(String type, String args)
	{
		xml.getAttributes().setComplex("transform", " ", false, type + "(" + args + ")");
		return this;
	}

	private Svg setUnitsAttrib(String AttributeName, Units units)
	{
		xml.getAttributes().set(AttributeName, units == null ? null : units.toString());
		return this;
	}

	private Svg setPointAttributes(Point point, String xName, String yName)
	{
		if (point != null) {
			xml.getAttributes()
					.setNumber(xName, point.getX(), getFormatter())
					.setNumber(yName, point.getY(), getFormatter());
		}
		return this;
	}

	private NumberFormatter getFormatter()
	{
		return ((SvgProperties) xml.getLanguageProperties()).getFormatter();
	}

	private NumberFormatter getDegreesFormatter()
	{
		return ((SvgProperties) xml.getLanguageProperties()).getDegreesFormatter();
	}

	public enum Units
	{
		USER_SPACE_ON_USE("userSpaceOnUse"), OBJECT_BOUNDING_BOX("objectBoundingBox");

		private String str;

		private Units(String str)
		{
			this.str = str;
		}

		@Override
		public String toString()
		{
			return str;
		}
	}

	public enum Align
	{
		NONE,
		X_MIN_Y_MIN, X_MIN_Y_MID, X_MIN_Y_MAX,
		X_MID_Y_MIN, X_MID_Y_MID, X_MID_Y_MAX,
		X_MAX_Y_MIN, X_MAX_Y_MID, X_MAX_Y_MAX;

		private String str;

		private Align()
		{
			str = name().toLowerCase().replace("x_m", "xM").replace("_y_m", "YM");
		}

		@Override
		public String toString()
		{
			return str;
		};
	}

	public enum MeetOrSlice
	{
		MEET, SLICE;

		private String str;

		private MeetOrSlice()
		{
			str = name().toLowerCase();
		}

		@Override
		public String toString()
		{
			return str;
		};
	}
}

