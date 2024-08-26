package com.MedicalSystem.Utils;

import com.MedicalSystem.Model.Patient;

public class LoggedInUserHolder {
	private static Patient loggedInUser;

    public static Patient getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Patient Patient) {
    	loggedInUser = Patient;
    }
}
