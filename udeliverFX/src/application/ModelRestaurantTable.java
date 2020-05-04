package application;

public class ModelRestaurantTable {
	public ModelRestaurantTable(String name, String wage, String weeklyHours, String weekPay, String weekTip,
			String totalinWeek, String allTime) {
		super();
		this.name = name;
		this.wage = wage;
		this.weeklyHours = weeklyHours;
		WeekPay = weekPay;
		WeekTip = weekTip;
		TotalinWeek = totalinWeek;
		AllTime = allTime;
	}

	String name,wage,weeklyHours,WeekPay,WeekTip,TotalinWeek,AllTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(String weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	public String getWeekPay() {
		return WeekPay;
	}

	public void setWeekPay(String weekPay) {
		WeekPay = weekPay;
	}

	public String getWeekTip() {
		return WeekTip;
	}

	public void setWeekTip(String weekTip) {
		WeekTip = weekTip;
	}

	public String getTotalinWeek() {
		return TotalinWeek;
	}

	public void setTotalinWeek(String totalinWeek) {
		TotalinWeek = totalinWeek;
	}

	public String getAllTime() {
		return AllTime;
	}

	public void setAllTime(String allTime) {
		AllTime = allTime;
	}
}
