package common;

import java.io.Serializable;

public class Block implements Serializable {
	private static final long serialVersionUID = 1377516559920421354L;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
