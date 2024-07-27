package api.utilities;

import java.util.Random;

public enum Status {
	placed,approved,delivered;

	  private static final Status[] VALUES = values();
	  private static final int SIZE = VALUES.length;
	  private static final Random RANDOM = new Random();

	  public static Status getRandomStatus()  {
	    return VALUES[RANDOM.nextInt(SIZE)];
	  }
	
}
