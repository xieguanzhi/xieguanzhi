package entity;

import java.io.Serializable;
import java.util.List;

public class PageResult<E> implements Serializable{
	
	private long total;
	
	private List<E> list;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public PageResult() {
		super();
	}
		
}
