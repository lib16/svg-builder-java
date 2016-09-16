package com.lib16.java.svg;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Path;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.svg.Svg.Align;
import com.lib16.java.svg.Svg.MeetOrSlice;
import com.lib16.java.svg.Svg.Units;
import com.lib16.java.utils.enums.Unit;
import com.lib16.java.xml.shared.TargetAttribute.Target;

public class SvgTest
{
	private static Point point = new Point(10, 20);
	private static Angle angle = Angle.byDegrees(45);

	@DataProvider(name = "provider")
	public static Object[][] provider()
	{
		SvgProperties tp = new DefaultSvgProperties()
		{
			@Override
			public boolean xmlDeclarationEnabled()
			{
				return false;
			}

			@Override
			public String getSvgVersion()
			{
				return "2.0";
			}
		};
		return new Object[][] {
			{
				Svg.createSvg(tp, 800, Unit.PX, 10, Unit.EM),
				"<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
						+ "version=\"2.0\" "
						+ "width=\"800px\" height=\"10em\"/>",
			},
			{
				Svg.createSvg(tp),
				"<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
						+ "version=\"2.0\"/>",
			},
			{
				Svg.createSvg(800, Unit.PX, 10, Unit.EM),
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
						+ "width=\"800px\" height=\"10em\"/>",
			},
			{
				Svg.createSvg(),
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\"/>",
			},
			{
				Svg.createSub().rect(point, 30, 40, 5, 2.5),
				"<rect x=\"10\" y=\"20\" width=\"30\" height=\"40\" rx=\"5\" ry=\"2.5\"/>"
			},
			{
				Svg.createSub().rect(point, 30, 40, 5),
				"<rect x=\"10\" y=\"20\" width=\"30\" height=\"40\" rx=\"5\"/>"
			},
			{
				Svg.createSub().rect(point, 30, 40),
				"<rect x=\"10\" y=\"20\" width=\"30\" height=\"40\"/>"
			},
			{
				Svg.createSub().circle(point, 30),
				"<circle cx=\"10\" cy=\"20\" r=\"30\"/>"
			},
			{
				Svg.createSub().ellipse(point, 30, 25),
				"<ellipse cx=\"10\" cy=\"20\" rx=\"30\" ry=\"25\"/>"
			},
			{
				Svg.createSub().line(point, new Point(30, 40)),
				"<line x1=\"10\" y1=\"20\" x2=\"30\" y2=\"40\"/>"
			},
			{
				Svg.createSub().polyline(point, new Point(30, 40), new Point(50, 60)),
				"<polyline points=\"10,20 30,40 50,60\"/>"
			},
			{
				Svg.createSub().polygon(point, new Point(30, 40), new Point(50, 60)),
				"<polygon points=\"10,20 30,40 50,60\"/>"
			},
			// path()
			{
				Svg.createSub().path(new Path().star(point, 4, 100)),
				"<path d=\"M 10,-80 L 110,20 L 10,120 L -90,20 Z\"/>"
			},
			// text()
			{
				Svg.createSub().text("lorem ipsum", new Point(20, 100)),
				"<text x=\"20\" y=\"100\">lorem ipsum</text>"
			},
			{
				Svg.createSub().text("lorem ipsum", new Point(0, 0)),
				"<text x=\"0\" y=\"0\">lorem ipsum</text>"
			},
			{
				Svg.createSub().text("lorem ipsum"),
				"<text>lorem ipsum</text>"
			},
			{
				Svg.createSub().text(),
				"<text/>"
			},
			// tspan()
			{
				Svg.createSub().tspan("lorem ipsum", new Point(30, 110)),
				"<tspan x=\"30\" y=\"110\">lorem ipsum</tspan>"
			},
			{
				Svg.createSub().tspan("lorem ipsum"),
				"<tspan>lorem ipsum</tspan>"
			},
			{
				Svg.createSub().tspan(),
				"<tspan/>"
			},
			// textPath()
			{
				Svg.createSub().textPath("lorem ipsum", "#path"),
				"<textPath xlink:href=\"#path\">lorem ipsum</textPath>"
			},
			// a()
			{
				Svg.createSub().a("https://github.com", Target.BLANK),
				"<a xlink:href=\"https://github.com\" target=\"_blank\"/>"
			},
			{
				Svg.createSub().a("https://github.com"),
				"<a xlink:href=\"https://github.com\"/>"
			},
			// use()
			{
				Svg.createSub().use("#circle", new Point(20, 30), 40, 50),
				"<use xlink:href=\"#circle\" x=\"20\" y=\"30\" width=\"40\" height=\"50\"/>"
			},
			{
				Svg.createSub().use("#circle", new Point(20, 40)),
				"<use xlink:href=\"#circle\" x=\"20\" y=\"40\"/>"
			},
			// defs()
			{
				Svg.createSub().defs().circle(new Point(0, 0), 20).setId("circle"),
				"<defs>\n\t<circle cx=\"0\" cy=\"0\" r=\"20\" id=\"circle\"/>\n</defs>"
			},
			// g()
			{
				Svg.createSub().g().circle(new Point(0, 0), 20),
				"<g>\n\t<circle cx=\"0\" cy=\"0\" r=\"20\"/>\n</g>"
			},
			// title(), desc()
			{
				Svg.createSub().title("Lorem Ipsum"),
				"<title>Lorem Ipsum</title>"
			},
			{
				Svg.createSub().desc("lorem ipsum"),
				"<desc>lorem ipsum</desc>"
			},
			// clipPath()
			{
				Svg.createSub().clipPath("p1", Units.USER_SPACE_ON_USE),
				"<clipPath id=\"p1\" clipPathUnits=\"userSpaceOnUse\"/>"
			},
			{
				Svg.createSub().clipPath("p1"),
				"<clipPath id=\"p1\"/>"
			},
			// mask()
			{
				Svg.createSub().mask("m1", new Point(10, 20), 200, 120,
						Units.OBJECT_BOUNDING_BOX, Units.USER_SPACE_ON_USE),
				"<mask id=\"m1\" x=\"10\" y=\"20\" width=\"200\" height=\"120\""
				+ " maskUnits=\"objectBoundingBox\" maskContentUnits=\"userSpaceOnUse\"/>"
			},
			{
				Svg.createSub().mask("m1", new Point(10, 20)),
				"<mask id=\"m1\" x=\"10\" y=\"20\"/>"
			},
			{
				Svg.createSub().mask("m1"),
				"<mask id=\"m1\"/>"
			},
			{
				Svg.createSvg().style(".h1 {\n\tfont-size: 20px;\n}"),
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">"
						+ "\n\t<style>\n\t\t.h1 {\n\t\t\tfont-size: 20px;\n\t\t}"
						+ "\n\t</style>\n</svg>"
			},
			{
				Svg.createSvg().script("/*\n *\n */"),
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<svg "
						+ "xmlns=\"http://www.w3.org/2000/svg\" "
						+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">"
						+ "\n\t<script>\n\t\t/*\n\t\t *\n\t\t */"
						+ "\n\t</script>\n</svg>"
			},
			// Attributes
			{
				Svg.createSub().circle(point, 100).addTranslate(50, 40),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"translate(50 40)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addScale(1.5, -2),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"scale(1.5 -2)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addScale(1.5),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"scale(1.5)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addRotate(angle),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"rotate(45)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addRotate(angle, point),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"rotate(45 10,20)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addSkewX(angle),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"skewX(45)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addSkewY(angle),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"skewY(45)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addMatrix(1, 2, 3, 4, 5, 6),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"matrix(1 2 3 4 5 6)\"/>"
			},
			{
				Svg.createSub().circle(point, 100).addScale(0.6, 1).addSkewX(angle),
				"<circle cx=\"10\" cy=\"20\" r=\"100\" transform=\"scale(0.6 1) skewX(45)\"/>"
			},
			{
				Svg.createSub().g().setClass("foo", "bar").setClass("baz"),
				"<g class=\"foo bar baz\"/>"
			},
			{
				Svg.createSub().text("foo bar").setDx(-5, 0),
				"<text dx=\"-5 0\">foo bar</text>"
			},
			{
				Svg.createSub().text("foo bar").setDy(-5, 0),
				"<text dy=\"-5 0\">foo bar</text>"
			},
			{
				Svg.createSub().image("image.jpg")
						.setPreserveAspectRatio(Align.X_MID_Y_MIN, MeetOrSlice.SLICE, true),
				"<image xlink:href=\"image.jpg\" preserveAspectRatio=\"defer xMidYMin slice\"/>"
			},
			{
				Svg.createSub().image("image.jpg")
						.setPreserveAspectRatio(Align.X_MID_Y_MIN, MeetOrSlice.SLICE),
				"<image xlink:href=\"image.jpg\" preserveAspectRatio=\"xMidYMin slice\"/>"
			},
			{
				Svg.createSub().image("image.jpg")
						.setPreserveAspectRatio(Align.X_MID_Y_MIN),
				"<image xlink:href=\"image.jpg\" preserveAspectRatio=\"xMidYMin\"/>"
			},
			{
				Svg.createSub().image("image.jpg").setPreserveAspectRatio(null),
				"<image xlink:href=\"image.jpg\"/>"
			},
			{
				Svg.createSub().text("foo bar").setRotate(
						Angle.byDegrees(15), Angle.byDegrees(10),
						Angle.byDegrees(5), Angle.byDegrees(0)),
				"<text rotate=\"15 10 5 0\">foo bar</text>"
			},
			{
				Svg.createSub().image("image.jpg").setViewBox(new Point(0, 0), 640, 400),
				"<image xlink:href=\"image.jpg\" viewBox=\"0 0 640 400\"/>"
			},
			{
				Svg.createSub().image("image.jpg").setViewBox(null, 640, 400),
				"<image xlink:href=\"image.jpg\"/>"
			},
		};
	}

	@Test(dataProvider = "provider")
	public void test(Svg actual, String expected)
	{
		Assert.assertEquals(actual.getXml().toString(), expected);
	}
}
