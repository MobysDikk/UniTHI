package ddd.models.simple;

import ddd.models.Wireframe;

public class Plane extends Wireframe {
	{
		vertices = new double[][] {
				{-1.0, 0.0, -1.0}, // 0
				{1.0, 0.0, -1.0},  // 1
				{-1.0, 0.0, 1.0},  // 2
				{1.0, 0.0, 1.0},   // 3
		};
		edges = new int[][] {
			{0, 1},
			{1, 3},
			{3, 2},
			{2, 0},
		};
	}
}
