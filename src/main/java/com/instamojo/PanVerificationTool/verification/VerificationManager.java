package com.instamojo.PanVerificationTool.verification;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.instamojo.PanVerificationTool.exception.ValidationException;
import com.instamojo.PanVerificationTool.object.Pan;

public class VerificationManager {

	Pan newPan = null;
	SimpleDateFormat df = null;
	private static final String datePattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

	public VerificationManager(Pan pan) {
		newPan = pan;
		df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
	}

	public void verify() throws ValidationException {
		checkPanNumberValidity();
		checkPanUserValidity();
		checkDatesValidity();
	}

	private void checkPanNumberValidity() throws ValidationException {
		String panNumber = newPan.getPanId();
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

		Matcher matcher = pattern.matcher(panNumber);
		// Check if pattern matches
		if (!matcher.matches()) {
			throw new ValidationException("The pan number is not a valid one");
		}
	}

	private void checkPanUserValidity() throws ValidationException {
		String userName = newPan.getUserName();
		String userFatherName = newPan.getUserFatherName();
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(userName);
		if (!matcher.matches()) {
			throw new ValidationException("The name is not a valid one");
		}
		matcher = pattern.matcher(userFatherName);
		if (!matcher.matches()) {
			throw new ValidationException("The father's name is not a valid one");
		}
	}

	private void checkDatesValidity() throws ValidationException {
		String dob = newPan.getDateOfBirth();
		String issueDate = newPan.getIssueDate();
		checkDateFormat(dob);
		checkDateTextValidity(dob);
		checkDateFormat(issueDate);
		checkDateTextValidity(issueDate);
	}

	private void checkDateFormat(String date) throws ValidationException {
		try {
			df.parse(date);
		} catch (Exception e) {
			throw new ValidationException("The date format is not valid", e);
		}

	}

	private void checkDateTextValidity(String date) throws ValidationException {
		Pattern pattern = Pattern.compile(datePattern);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			throw new ValidationException("The date is not a valid date");
		}
	}
}
