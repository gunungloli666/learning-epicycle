package fjr.com.epi.cycle;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animasi extends Application{

	private Canvas canvas;
	private Scene scene; 
	
	private GraphicsContext gc; 
	
	
	private double width = 700; 
	private double height = 500; 
	
	int numCircles = 10; 
	
	private ArrayList<Circle > listOfCircle;
	
	private double centerMainCircleX = width/2.; 
	private double centerMainCircleY = height/2.; 
	
	private double radiusMainCircle = 100; 
	
	double[] listSpeedAngle = new double[numCircles]; 
	
	double currentTimeStep = 4;
	
	
	Timeline timeLine; 
	
	double angle = 30; 
	 
	double currentStrokeX ; 
	double currentStrokeY ;
	
	double pastStrokeX; 
	double pastStrokeY; 

	private ArrayList< Point2D > listOfPoint = new  ArrayList<Point2D>() ;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group(); 
		
		canvas = new Canvas(width + 50 , height + 50); 
		gc = canvas.getGraphicsContext2D(); 
		
		root.getChildren().add( canvas); 
		
		primaryStage.setScene(new Scene(root)) ;
		primaryStage.show(); 
		
		generateCircles();
		redraw(gc); 
		
		timeLine = new Timeline(); 
		
		KeyFrame kf = new KeyFrame( Duration.millis(20), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateCirclesStatus(angle+= 0.001); 
				redraw(gc); 
			}
		}); 
		
		timeLine.getKeyFrames().addAll(kf); 
		
		timeLine.setAutoReverse(false); 
		timeLine.setCycleCount(Animation.INDEFINITE); 
		
		timeLine.play(); 
	}

	
	public void generateCircles(){

		Random r = new Random(); 
		double awal = 2; 
		for( int i =0; i< numCircles ;i++){
			listSpeedAngle[i] = awal;
			awal  = awal *2 ; 
		}
		
		listOfCircle = new ArrayList<Circle>();

		double currentX = centerMainCircleX; 
		double currentY = centerMainCircleY; 
		
		double currentRadius = radiusMainCircle; 
		
		double currentAngle = 0.; 
		
		for(int i=0; i < numCircles; i++){
			Circle c = new Circle(); 
			c.setCenterX(currentX);
			c.setCenterY(currentY); 
			c.setRadius(currentRadius); 
			
			listOfCircle.add(c); 
			
			currentAngle = currentAngle + 20; 
			
			double x = currentRadius * Math.cos(currentAngle); 
			double y = currentRadius * Math.sin(currentAngle); 
			
			currentX = currentX + x; 
			currentY = currentY - y; 
			
			currentRadius = currentRadius * 0.6; 
			
		}
		
		int m = listOfCircle.size(); 
		
		pastStrokeX = listOfCircle.get(m-1).getCenterX();
		pastStrokeY = listOfCircle.get(m-1).getCenterY(); 
		
	}
	
	public void updateCirclesStatus(double angle ){
		
		double currentX = centerMainCircleX; 
		double currentY = centerMainCircleY; 
		
		double currentAngle =  angle;  

		for(int i=0; i< numCircles ;i++){
			
			Circle c = listOfCircle.get(i); 
						
			c.setCenterX(currentX);
			c.setCenterY(currentY); 

//			currentAngle = currentAngle + listSpeedAngle[i] * currentTimeStep;
			currentAngle = currentAngle * 2; 
			
			double x = c.getRadius() * Math.cos(currentAngle); 
			double y = c.getRadius() * Math.sin(currentAngle); 
			
			currentX = currentX + x; 
			currentY = currentY - y; 
			
		}
	}

	
	public void redraw(GraphicsContext gc){
		
		gc.setFill(Color.WHITE); 
		gc.fillRect(0, 0, canvas.getWidth() , canvas.getHeight() );
		
		gc.setStroke(Color.BLACK );
		for(int i=0; i< listOfCircle.size() ;i++){
			Circle c = listOfCircle.get(i); 
			gc.strokeOval( c.getCenterX() - c.getRadius() , c.getCenterY() - c.getRadius() ,
					c.getRadius() * 2 , c.getRadius() * 2  );
		}
		
	
		
		int m = listOfCircle.size(); 
//		
		currentStrokeX = listOfCircle.get(m-1).getCenterX();
		currentStrokeY = listOfCircle.get(m-1).getCenterY(); 
		
		Point2D p = new Point2D(currentStrokeX, currentStrokeY); 
		
		listOfPoint.add(p); 
//		
		gc.setStroke(Color.RED); 
		if(listOfPoint.size() > 1){
//			gc.moveTo(listOfPoint.get(0).getX(),  listOfPoint.get(0).getY() );
//			for(int i=1; i <  listOfPoint.size(); i++){
//				gc.lineTo(listOfPoint.get(i).getX(), listOfPoint.get(i).getY() );
//			}
//			gc.stroke();
			int awal = 0; 
			int akhir= 1; 
			for(int i=0; i< listOfPoint.size()-1 ;i++){
				gc.strokeLine(listOfPoint.get(awal).getX() , listOfPoint.get(awal).getY(), 
						listOfPoint.get(akhir).getX() , listOfPoint.get(akhir).getY());
				awal++; 
				akhir++; 
			}
			
		}
		
		if(listOfPoint.size() > 100){
			ArrayList<Point2D> p_temp = new ArrayList<Point2D>(); 
//			for(int i = 0; i< 10; i++){
//				p_temp.add(listOfPoint.get(i));
//			}
//			for(int i =0; i< )
//			System.out.println("remove");
//			listOfPoint.clear();
			for( int i= 0; i< 2; i++){
				listOfPoint.remove(0) ; 
			}
		}
	}
	
	
	public static void main(String[] args){
		launch(args); 
	}
	
}
