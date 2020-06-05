import java.util.List;

public class NonPeriodicUniformBSpline extends BSpline{

	private int knotVektorLength;
	private int k2;
	private int i2;
	private int inc=1;
	
	//Knotenvektor wird initialisiert mit point länge + k
	// wenn k=2 und länge 9 => 001234566
	// wenn k=4 und länge 9 => 000012222
	
	NonPeriodicUniformBSpline(List<Point> points, int k, double h) {
		super(points, k, h);
		this.knotVektorLength=points.size()+k;
		this.k2=k;
		
		 super.knotVector= new double[knotVektorLength];
		 
		 for(int i =0;i<knotVektorLength;i++) {
			 if(i<k) {
				 knotVector[i]=0; 
			 }
			 if(i>=k && i<knotVektorLength-k) {
				 knotVector[i]=inc;
				 inc++;
			 }
			 if(i>=knotVektorLength-k) {
				 knotVector[i]=inc;
			 }
			 
			 
		 }
		 // Unelegant!
//		 for(int i =0;i<k;i++) {
//			 knotVector[i]=0;
//		 }
//		 
//		 for(int i = 1;i<knotVektorLength-k-k;i++) {
//			 knotVector[k2]=i;
//			 k2++;
//			 i2=i;
//		 }
//	
//		 i2++;
//		 for(int i = knotVektorLength-k;i<knotVektorLength;i++) {
//			 knotVector[i]= i2;
//		 }
		 
	}

}
