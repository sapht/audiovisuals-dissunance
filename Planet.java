// planet.java
import processing.core.*;

class Planet{
  float x, y, r, d, m;
	float max_x, max_y, min_x, min_y;
	boolean dolog;
	int freq;
	PVector vel;

  // default constructor
  //public void Planet() {
  //}

  public Planet(float x, float y, float r, PVector vel) {
    this.x = x;
    this.y = y;
		this.max_x = 0;
		this.max_y = 0;
		this.min_x = -1000;
		this.min_y = -1000;
		this.dolog = false;
    this.r = r;
    this.d = r * 2;
		this.vel = vel;
    m = r*0.1f;
  }

	public void moveIt(Planet sun, float dt) {
		this.dolog=false;
		if ( this.x > this.max_x ) {
			this.max_x = this.x;
			this.dolog=true;
		}
		if ( this.x < this.min_x ) {
			this.min_x = this.x;
			this.dolog=true;
		}
		if ( this.y > this.max_y ) {
			this.max_y = this.y;
			this.dolog=true;
		}
		if ( this.y < this.min_y ) {
			this.min_y = this.y;
			this.dolog=true;
		}
		double dist = Math.sqrt (
			Math.pow(sun.y - this.y, 2)
			+ Math.pow(sun.x - this.x, 2)
		);
		
		double a = 10000000 * ((this.r * sun.r)/(dist*dist));
		
		if ( this.x <= sun.x ) /// a ..... b 
			this.vel.x += a * Math.pow(dt, 1); // a--> ..... b
		else /// b ...... a
			this.vel.x -= a * Math.pow(dt, 1); //  b ..... <--a
		//this.vel.y += F;
		//if ( this.vel.x < 0 ) this.vel.x = 0;
		//if ( this.vel.y < 0 ) this.vel.y = 0;

		this.x += this.vel.x * dt * dt;
		//this.y += this.vel.y * dt;
	}
}
