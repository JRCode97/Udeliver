
package drivingInterface;



public class Job {
    private String nameOfEmployer;
    private int workweek;
    private int basePay;
    public Job(String NameOfEmployer,int weeklyHours,int PayRate)
    {
        nameOfEmployer = NameOfEmployer;
        workweek = weeklyHours;
        basePay = PayRate;
    }

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

    public int getWorkweek() {
        return workweek;
    }

    public void setWorkweek(int workweek) {
        this.workweek = workweek;
    }

    public int getBasePay() {
        return basePay;
    }

    public void setBasePay(int basePay) {
        this.basePay = basePay;
    }
    
}





