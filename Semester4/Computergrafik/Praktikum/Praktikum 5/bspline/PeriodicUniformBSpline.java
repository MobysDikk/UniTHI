import java.util.List;

public class PeriodicUniformBSpline extends BSpline{

	private int knotVektorLength;

	//Knotenvektor wird initialisiert mit point länge + k
	// knoten länge von 0 bis .... füllen.
	
	
	PeriodicUniformBSpline(List<Point> points, int k, double h) {
		super(points, k, h);
		this.knotVektorLength=points.size()+k;
		
		super.knotVector =new double[knotVektorLength];
		for(int i  =0;i<knotVektorLength;i++) {
			knotVector[i]=i;
		}
		
	}

}
