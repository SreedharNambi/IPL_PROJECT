package mypackage.iplproject;

public class Match {

    private String matchId;

    private String season;

    private String city;

    private String winningTeam;



    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

}
