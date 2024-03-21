package engine;

/**
 * contains methods returning keyboard values
 * @author filip
 *
 */
public abstract class InputHandler {
	
	
    public int getUp() {
        return 87;
    }
    
    public int getDown() {
        return 83;
    }

    public int getLeft() {
        return 65;
    }

    public int getRight() {
        return 68;
    }

    public int getArrowUp() {
        return 38;
    }

    public int getArrowDown() {
        return 40;
    }

    public int getArrowLeft() {
        return 37;
    }

    public int getArrowRight() {
        return 39;
    }
}
