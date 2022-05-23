package mypackage.iplproject;

public class Delivery {
    private String deliveryMatchId;

    private String bowlingTeam;

    private String bowler;

    private String extraRuns;

    private String totalRuns;


    public String getDeliveryMatchId() {
        return deliveryMatchId;
    }

    public void setDeliveryMatchId(String deliveryMatchId) {
        this.deliveryMatchId = deliveryMatchId;
    }

    public String getBowlingTeam(String record) {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(String extraRuns) {
        this.extraRuns = extraRuns;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(String totalRuns) {
        this.totalRuns = totalRuns;
    }
}
