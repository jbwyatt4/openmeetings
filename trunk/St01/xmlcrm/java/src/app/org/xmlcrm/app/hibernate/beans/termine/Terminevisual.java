package org.xmlcrm.app.hibernate.beans.termine;

public class Terminevisual {
    private Terminestruktur[] terminestruktur;
    private String comment;
    private String visualdata;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Terminestruktur[] getTerminestruktur() {
        return terminestruktur;
    }
    public void setTerminestruktur(Terminestruktur[] terminestruktur) {
        this.terminestruktur = terminestruktur;
    }
	public String getVisualdata() {
		return visualdata;
	}
	public void setVisualdata(String visualdata) {
		this.visualdata = visualdata;
	}
}
