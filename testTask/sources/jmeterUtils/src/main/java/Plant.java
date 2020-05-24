public class Plant {
    private String common;
    private String botanical;

    public Plant() {
    }

    public void setCommon(String common) {
        this.common = common == null ? "" : common;
    }

    public void setBotanical(String botanical) {
        this.botanical = botanical == null ? "" : botanical;
    }

    @Override
    public String toString() {
        return String.format("%s %s", common, botanical);
    }
}
