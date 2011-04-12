package by.giava.gestionechalet.model;


import java.io.Serializable;
import java.util.Date;

public class DaySummary implements Serializable {
	private Long num;
	private StringBuffer description;
	private Date data;

	public DaySummary(Long num, String description, Date data) {
		this.num = num;
		this.description = new StringBuffer();
		this.data = data;
	}

	public Long getNum() {
		if (num == null)
			num = new Long(0);
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public void inc() {
		this.num = getNum() + 1;
	}

	public String getDescription() {
		return description.toString();
	}

	public void addDescription(String description) {
		this.description.append(description + "<br/>");
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
