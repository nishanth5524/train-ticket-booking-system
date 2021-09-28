
public class WaitingListQueue {

	private String pnr;
	private String status;
	private String depdate;
	private String WL;
	private String pid;
	private int id;

	public String getPnr() {
		return pnr;
	}

	public String getStatus() {
		return status;
	}

	public String getDepdate() {
		return depdate;
	}

	public String getWL() {
		return WL;
	}

	public String getPid() {
		return pid;
	}

	public int getId() {
		return id;
	}

	public WaitingListQueue(String pnr, String status, String depdate, String WL, String pid, int id) {

		this.pnr = pnr;
		this.status = status;
		this.depdate = depdate;
		this.WL = WL;
		this.pid = pid;
		this.id = id;

	}

}
