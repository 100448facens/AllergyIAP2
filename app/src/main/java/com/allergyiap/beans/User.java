package com.allergyiap.beans;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long iduser;
	private String user_name;
	private String user_second_name;
	private String user_password;
	private String user_mail;
	private int user_station_default;
	private String alarm_weekdays;
	private String alarm_time;

	public User() {
	}

	public User(long id, String userName, String userSecondName, String userMail, String userPassword,
			int user_station_default, String alarm_weekdays, String alarm_time) {
		this.iduser = id;
		this.user_name = userName;
		this.user_second_name = userSecondName;
		this.user_password = userPassword;
		this.user_mail = userMail;
		this.user_station_default = user_station_default;
		this.alarm_weekdays = alarm_weekdays;
		this.alarm_time = alarm_time;
	}
	
	public User(String userName, String userSecondName, String userMail, String userPassword,
			int user_station_default, String alarm_weekdays, String alarm_time) {
		this.user_name = userName;
		this.user_second_name = userSecondName;
		this.user_password = userPassword;
		this.user_mail = userMail;
		this.user_station_default = user_station_default;
		this.alarm_weekdays = alarm_weekdays;
		this.alarm_time = alarm_time;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long id) {
		this.iduser = id;
	}
	
	public String getAlarm_weekdays() {
		return alarm_weekdays;
	}

	public void setAlarm_weekdays(String alarm_weekdays) {
		this.alarm_weekdays = alarm_weekdays;
	}
	
	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		this.user_name = userName;
	}

	public String getUser_second_name() {
		return user_second_name;
	}
	
	public int getUser_station_default(){
		return this.user_station_default;
	}
	
	public void setUser_station_default(int user_station_default){
		this.user_station_default = user_station_default;
	}

	public void setUser_second_name(String userSecondName) {
		this.user_second_name = userSecondName;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String userPassword) {
		this.user_password = userPassword;
	}
	
	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String userMail) {
		this.user_mail = userMail;
	}
}
