package org.xmlcrm.app.hibernate.beans.termine;

public class Terminestruktur {
    private Termine_User[] termine_user;
    private Terminegroups[] termine_groups;  
    private Terminesinglevisual[] terminesinglevisual;
    private Terminemiddletvisual[] terminemiddletvisual;
    public Terminegroups[] getTermine_groups() {
        return termine_groups;
    }
    public void setTermine_groups(Terminegroups[] termine_groups) {
        this.termine_groups = termine_groups;
    }
    public Termine_User[] getTermine_user() {
        return termine_user;
    }
    public void setTermine_user(Termine_User[] termine_user) {
        this.termine_user = termine_user;
    }
    public Terminemiddletvisual[] getTerminemiddletvisual() {
        return terminemiddletvisual;
    }
    public void setTerminemiddletvisual(Terminemiddletvisual[] terminemiddletvisual) {
        this.terminemiddletvisual = terminemiddletvisual;
    }
    public Terminesinglevisual[] getTerminesinglevisual() {
        return terminesinglevisual;
    }
    public void setTerminesinglevisual(Terminesinglevisual[] terminesinglevisual) {
        this.terminesinglevisual = terminesinglevisual;
    }

}
