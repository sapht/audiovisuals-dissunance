import processing.core.*;
import processing.opengl.*;
//import oscP5.*;
//import netP5.*;

public class Space extends PApplet
{
	//OscP5 osc;
	//NetAddress s;

	int scrn_w;
	int scrn_h;

	Planet sun = new  Planet(320, 320, 50, new PVector (0, 0));

	Planet[] balls =  { 
		new Planet(50, 160, 10, new PVector(0.0f, -0.0f))
	};

	long now;
	long last;

	public void setup() 
	{
		frameRate(100000);
		scrn_w = 640;
		scrn_h = 640;
		size(scrn_w, scrn_h, OPENGL);
		last = System.currentTimeMillis();
	}
	public void stop () 
	{
		//for ( int i=0; i < balls.length; i++ ) {
			//OscMessage msg = new OscMessage("/n_free");
			//msg.add(4000 + i);
			//osc.send(msg, s);
		//}
	}

	public void osctest()
	{
			//double speed = Math.sqrt(
						//Math.pow(vels[i].x, 2) + 
						//Math.pow(vels[i].y, 2) );
			//OscMessage msg = new OscMessage("/n_set");
			//msg.add(4000 + i);
			//msg.add("speed");
			//msg.add((float) speed);
			//osc.send(msg, s);

			//double y = (double) ((balls[i].y - (scrn_h/2)) / (scrn_h/2));
			//msg = new OscMessage("/n_set");
			//msg.add(4000 + i);
			//msg.add("y");
			//msg.add((float) y);
			//osc.send(msg, s);
			//println("Sending b[" + i + "] y: " + y );

			//double x = (double) ((balls[i].x - (scrn_w/2)) / (scrn_w/2));
			//msg = new OscMessage("/n_set");
			//msg.add(4000 + i);
			//msg.add("x");
			//msg.add((float) x);
			//osc.send(msg, s);
	}

	public void draw() 
	{
		// TODO draw FPS here
		background(0);
		fill(204);
		ellipse(sun.x, sun.y, sun.d, sun.d);
		long now = System.currentTimeMillis(); float dt = (now - last)/1000.f; last = now;

		for (int i=0; i < balls.length; i++){
			balls[i].moveIt(sun, dt);
			//println(balls[i].x);

			if ( balls[i].dolog ) {
				println(balls[i].x);
				//println(balls[i].x);
			}
			//balls[i].x += vels[i].x;
			//balls[i].y += vels[i].y;

			ellipse(balls[i].x, balls[i].y, balls[i].d, balls[i].d);
			//checkBoundaryCollision(balls[i], balls[i].vel);
		}

		//println(balls[0].vel.x);

		fill(255, 200, 0);
		//ellipse(scrn_w/2, scrn_h/2, 200, 200);

		checkObjectCollisions();
	}

	void checkObjectCollisions()
	{
	}

	void checkBoundaryCollision(Planet ball, PVector vel) 
	{
		if (ball.x > width - ball.r) {
			ball.x = width - ball.r;
			vel.x *= -1;
		} 
		else if (ball.x < ball.r) {
			ball.x = ball.r;
			vel.x *= -1;
		} 
		else if (ball.y > height-ball.r) {
			ball.y = height-ball.r;
			vel.y *= -1;
		} 
		else if (ball.y < ball.r) {
			ball.y = ball.r;
			vel.y *= -1;
		}
	}

	public static void main ( String [] args )
	{
		PApplet.main ( new String [] {
			"Space"
		});
	}
}
