

class vec2
{
    float x = 0.f;
    float y = 0.f;

    public vec2(){}

    public vec2(vec2 v)
    {
        this.x = v.x;
        this.y = v.y;
    }
    
    public vec2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public vec2 sub(vec2 v)
    {
        return new vec2(this.x - v.x, this.y-v.y);
    }

    public void neg()
    {
        this.x = -this.x;
        this.y = -this.y;
    }

    public float len()
    {
        return (float)Math.sqrt(this.x*this.x + this.y*this.y);
    }
}

class Body
{
    vec2 vel = new vec2();
    vec2 pos = new vec2();
    float r = 1.f;

    public Body(float r, float x, float y)
    {
        this.r = r;
        this.pos.x = x;
        this.pos.y = y;
    }

    public void integrate(float dt, vec2 g)
    {
        this.vel.x += g.x * dt;
        this.vel.y += g.y * dt;
        this.pos.x += this.vel.x * dt;
        this.pos.y += this.vel.y * dt;
    }

    public void draw()
    {
        fill(255,255,255);
        ellipse(this.pos.x, this.pos.y, this.r, this.r);
    }
}

static int NUM_PLANETS = 20;
Body planets[] = new Body[NUM_PLANETS];
Body sun = new Body(20.f, 250, 300);

long last = System.currentTimeMillis();

void setup()
{
    size(600, 600);
    
    Random r = new Random();
    
    for (int x=0; x<NUM_PLANETS; x++) {
        planets[x] = new Body(6+r.nextInt(6), r.nextInt(600), r.nextInt(600));
        planets[x].vel.x = -20+(float)r.nextInt(40);
        planets[x].vel.y = -20+(float)r.nextInt(40);
    }
}

void draw()
{
    background(0);

    long now = System.currentTimeMillis();

    float dt = (now-last)/1000.f;
    last = now;

    for (int x=0; x<NUM_PLANETS; x++) {
        Body p = planets[x];
        vec2 dir = sun.pos.sub(p.pos);
        vec2 a = new vec2(dir);

        float len = dir.len();
        a.x /= len;
        a.y /= len;

        if (len > sun.r) {
            float adist = 100000.f/(len*len);

            a.x*=adist;
            a.y*=adist;

            p.integrate(dt, a);
        } else {
            a.x = 0;
            a.y = 0;
            p.integrate(dt, a);
        }
        p.draw();
    }
    sun.draw();
}

