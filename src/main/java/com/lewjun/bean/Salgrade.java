package com.lewjun.bean;

public class Salgrade {
	private Integer	grade;

	private Integer	losal;

	private Integer	hisal;

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getLosal() {
		return losal;
	}

	public void setLosal(Integer losal) {
		this.losal = losal;
	}

	public Integer getHisal() {
		return hisal;
	}

	public void setHisal(Integer hisal) {
		this.hisal = hisal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Salgrade [grade=" + grade + ", losal=" + losal + ", hisal=" + hisal + "]";
	}
}