package springSpiel;

public class Punkt {

	private int m_x;
	private int m_y;
	private boolean m_blocked;

	Punkt() {
		m_x = 0;
		m_y = 0;
		m_blocked = true;
	}

	Punkt(int x, int y) {
		m_x = x;
		m_y = y;
		m_blocked = true;
	}

	Punkt(int x, int y, boolean isBlocked) {
		m_x = x;
		m_y = y;
		m_blocked = isBlocked;
	}
	
	public int getX(){
		return m_x;
	}
	
	public int getY(){
		return m_y;
	}
	
	public boolean getBlocked(){
		return m_blocked;
	}
	
	public boolean toogleState(){
		m_blocked = !(m_blocked);
		return m_blocked;
	}
}
