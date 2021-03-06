package ddd.models.simple;

import ddd.models.Wireframe;

public class House extends Wireframe {
	{
		vertices = new double[][] {
			{-1.0, 0.0, -2.0},  // 0
			{1.0, 0.0, -2.0},   // 1
			{1.0, 0.0, 2.0},    // 2
			{-1.0, 0.0, 2.0},   // 3
			{-1.0, 1.0, -2.0},  // 4
			{1.0, 1.0, -2.0},   // 5
			{1.0, 1.0, 2.0},    // 6
			{-1.0, 1.0, 2.0},   // 7
			{0.0, 2.0, -2.0},   // 8
			{0.0, 2.0, 2.0},    // 9
		};
		
		edges = new int[][] {
			{0, 1},
			{1, 2},
			{2, 3},
			{3, 0},
			{0, 4},
			{4, 8},
			{8, 5},
			{5, 1},
			{3, 7},
			{7, 9},
			{9, 6},
			{6, 2},
			{5, 6},
			{8, 9},
			{4, 7},
		};
	}
}
