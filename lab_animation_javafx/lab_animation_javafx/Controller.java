// Name: William Cantera
// I worked alone

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller extends Application {
	//data fields hold Model and View
	private Model model;
	private View view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage theStage) {
        view = new View(theStage);
		model = new Model(view.getWidth(), view.getHeight(), 
                view.getImageWidth(), view.getImageHeight());
		
		view.theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	public void handle(KeyEvent event) {
	                KeyCode keyCode = ((KeyEvent) event).getCode();	                
	                if (keyCode == KeyCode.DIGIT1) { // Confuse mode
		                view.modeInd = 1;
		                double halfSpeedX = model.getXincr()/2;
		                double halfSpeedY = model.getYincr()/2;
		                model.setXincr(halfSpeedX);
		                model.setYincr(halfSpeedY);
	                } else if (keyCode == KeyCode.DIGIT2) { // Default mode
		                view.modeInd = 0;		  
		                double originalVelocityX = 8;
		                double originalVelocityY = 2;
		                model.setXincr(originalVelocityX);
		                model.setYincr(originalVelocityY);
	                } else if (keyCode == KeyCode.DIGIT3) { // Attac mode
		                view.modeInd = 2;
		                double doubleSpeedX = 2*model.getXincr();
		                double doubleSpeedY = 2*model.getYincr();
		                model.setXincr(doubleSpeedX);
		                model.setYincr(doubleSpeedY);
	                }
	                
	                if (keyCode == KeyCode.LEFT) {
	                	model.setDirection(Direction.WEST);
	                } else if (keyCode == KeyCode.RIGHT) {
	                	model.setDirection(Direction.EAST);
	                } else if (keyCode == KeyCode.UP) {
	                	model.setDirection(Direction.NORTH);
	                } else if (keyCode == KeyCode.DOWN) {
	                	model.setDirection(Direction.SOUTH);
	                }
	            }
        	}
        );
        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                //increment the x and y coordinates, alter direction if necessary
                model.updateLocationandDirection();
                //input the x coordinates, y coordinates, and Direction (see enum class)
                view.update(model.getX(), model.getY(), model.getDirection());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.show();
    }

}
