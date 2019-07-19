import java.awt.Color;

public enum Status{
		EMPTY(Color.BLUE),
		HIT(Color.RED),
		MISS(Color.GRAY);
		
		private Color c;
		private Status(Color c) {
			this.c = c;
		}
		
		public Color getColor() {
			return c;
		}
	}
