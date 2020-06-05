import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ddd.transform.Transform;
import ddd.transform.affine.GeneralRotation;
import ddd.transform.affine.RotationX;
import ddd.transform.affine.RotationY;
import ddd.transform.affine.RotationZ;
import ddd.transform.affine.Scale;
import ddd.transform.affine.Translation;

public class TransformsTest {
	@Test
	public void testTranslation() {
		//@formatter:off
		Transform soll = new Transform(new double[][] { 
			{ 1.0, 0.0, 0.0, -0.1 }, 
			{ 0.0, 1.0, 0.0, 0.22 },
			{ 0.0, 0.0, 1.0, -0.333 }, 
			{ 0.0, 0.0, 0.0, 1.0 }, });
		//@formatter:on
		Transform ist = new Translation(-0.1, 0.22, -0.333);
		assertEquals("Tranlation um (-0.1, 0.22, -0.333) sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
				Transform.maxNorm(soll, ist), 1.0e-12);
	}

	@Test
	public void testScale() {
		//@formatter:off
		Transform soll1 = new Transform(new double[][] { 
			{ 1.1e9, 0.0, 0.0, 0.0 }, 
			{ 0.0, -1.22, 0.0, 0.0 },
			{ 0.0, 0.0, 2.33, 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 }, });
		//@formatter:on
		Transform ist1 = new Scale(1.1e9, -1.22, 2.33);
		assertEquals("Skalierung mit 1.1e9, -1.22, 2.33 sollte sein:\n" + soll1 + "ist:\n" + ist1, 0.0,
				Transform.maxNorm(soll1, ist1), 1.0e-12);
		//@formatter:off
		Transform soll2 = new Transform(new double[][] { 
			{ -Math.PI, 0.0, 0.0, 0.0 }, 
			{ 0.0, -Math.PI, 0.0, 0.0 },
			{ 0.0, 0.0, -Math.PI, 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 }, });
		//@formatter:on
		Transform ist2 = new Scale(-Math.PI);
		assertEquals("Zentrische Streckung mit -π sollte sein:\n" + soll2 + "ist:\n" + ist2, 0.0,
				Transform.maxNorm(soll2, ist2), 1.0e-12);
	}

	@Test
	public void testRotationX() {
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{ 1.0000000, 0.0000000,  0.0000000, 0.0000000},
			{ 0.0000000, 0.2588190, -0.9659258, 0.0000000},
			{ 0.0000000, 0.9659258,  0.2588190, 0.0000000},
			{ 0.0000000, 0.0000000,  0.0000000, 1.0000000},});
		//@formatter:on
		int deg = 75;
		double radians = Math.toRadians(deg);
		Transform ist1 = new RotationX(radians);
		assertEquals("Rotation von " + deg + "° um x-Achse sollte sein:\n" + soll + "ist:\n" + ist1, 0.0,
				Transform.maxNorm(soll, ist1), 1.0e-6);
		Transform ist2 = new RotationX(Math.sin(radians), Math.cos(radians));
		assertEquals("Rotation von " + deg + "° um x-Achse sollte sein:\n" + soll + "ist:\n" + ist2, 0.0,
				Transform.maxNorm(soll, ist2), 1.0e-6);
	}

	@Test
	public void testRotationY() {
		double sqrt = 0.5 * Math.sqrt(2.0);
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{ -sqrt, 0.000,  sqrt, 0.000},
			{ 0.000, 1.000, 0.000, 0.000},
			{ -sqrt, 0.000, -sqrt, 0.000},
			{ 0.000, 0.000, 0.000, 1.000},});
		//@formatter:on
		int deg = 135;
		double radians = Math.toRadians(deg);
		Transform ist1 = new RotationY(radians);
		assertEquals("Rotation von " + deg + "° um y-Achse sollte sein:\n" + soll + "ist:\n" + ist1, 0.0,
				Transform.maxNorm(soll, ist1), 1.0e-12);
		Transform ist2 = new RotationY(Math.sin(radians), Math.cos(radians));
		assertEquals("Rotation von " + deg + "° um y-Achse sollte sein:\n" + soll + "ist:\n" + ist2, 0.0,
				Transform.maxNorm(soll, ist2), 1.0e-12);
	}

	@Test
	public void testRotationZ() {
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.8191521, 0.5735765, 0.0000000, 0.0000000},
			{ -0.5735765, 0.8191521, 0.0000000, 0.0000000},
			{  0.0000000, 0.0000000, 1.0000000, 0.0000000},
			{  0.0000000, 0.0000000, 0.0000000, 1.0000000},});
		//@formatter:on
		int deg = -35;
		double radians = Math.toRadians(deg);
		Transform ist1 = new RotationZ(radians);
		assertEquals("Rotation von " + deg + "° um z-Achse sollte sein:\n" + soll + "ist:\n" + ist1, 0.0,
				Transform.maxNorm(soll, ist1), 1.0e-6);
		Transform ist2 = new RotationZ(Math.sin(radians), Math.cos(radians));
		assertEquals("Rotation von " + deg + "° um z-Achse sollte sein:\n" + soll + "ist:\n" + ist2, 0.0,
				Transform.maxNorm(soll, ist2), 1.0e-6);
	}

	@Test
	public void testGeneralRotation() {
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.8191521, 0.5735765, 0.0000000, 0.0000000},
			{ -0.5735765, 0.8191521, 0.0000000, 0.0000000},
			{  0.0000000, 0.0000000, 1.0000000, 0.0000000},
			{  0.0000000, 0.0000000, 0.0000000, 1.0000000},});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			Transform ist = new GeneralRotation(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, radians);
			assertEquals("Rotation von " + deg + "° um z-Achse sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.8191521, 0.5735765, 0.0000000, -0.966305},
			{ -0.5735765, 0.8191521, 0.0000000, 0.935272},
			{  0.0000000, 0.0000000, 1.0000000, 0.0000000},
			{  0.0000000, 0.0000000, 0.0000000, 1.0000000},});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			Transform ist = new GeneralRotation(1.0, 2.0, 3.0, 0.0, 0.0, 1.0, radians);
			assertEquals("Rotation von " + deg + "° um z-Achse um Raumpunkt sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.879435,  0.270872,  0.391437, -1.595490 },
		    { -0.391437,  0.879435,  0.270872, -0.180048 },
			{ -0.270872, -0.391437,  0.879435,  1.415442 },
			{  0.000000,  0.000000,  0.000000,  1.000000 },});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			double sqrt = Math.sqrt(3.0) / 3.0;
			Transform ist = new GeneralRotation(1.0, 2.0, 3.0, sqrt, -sqrt, sqrt, radians);
			assertEquals("Rotation von " + deg + "° um schräge Achse an Raumpunkt sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.879435,  0.270872,  0.391437, -1.595490 },
		    { -0.391437,  0.879435,  0.270872, -0.180048 },
			{ -0.270872, -0.391437,  0.879435,  1.415442 },
			{  0.000000,  0.000000,  0.000000,  1.000000 },});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			Transform ist = new GeneralRotation(1.0, 2.0, 3.0, 1.0, -1.0, 1.0, radians);
			assertEquals("Rotation von " + deg + "° um nicht-normierte schräge Achse an Raumpunkt sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.819152,  0.000000, -0.573576,  1.901577 },
			{  0.000000,  1.000000,  0.000000,  0.000000 },
			{  0.573576,  0.000000,  0.819152, -0.031033 },
			{  0.000000,  0.000000,  0.000000,  1.000000 },});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			Transform ist = new GeneralRotation(1.0, 2.0, 3.0, 0.0, 1.0, 0.0, radians);
			assertEquals("Rotation von " + deg + "° um y-Achse an Raumpunkt sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
		{
		//@formatter:off
		Transform soll = new Transform(new double[][] {
			{  0.819152,  0.000000, -0.573576,  1.901577 },
			{  0.000000,  1.000000,  0.000000,  0.000000 },
			{  0.573576,  0.000000,  0.819152, -0.031033 },
			{  0.000000,  0.000000,  0.000000,  1.000000 },});
		//@formatter:on
			int deg = -35;
			double radians = Math.toRadians(deg);
			Transform ist = new GeneralRotation(1.0, 2.0, 3.0, -1e-200, 1.0, 1e-300, radians);
			assertEquals("Rotation von " + deg + "° um y-Achse an Raumpunkt sollte sein:\n" + soll + "ist:\n" + ist, 0.0,
					Transform.maxNorm(soll, ist), 1.0e-6);
		}
	}
}
