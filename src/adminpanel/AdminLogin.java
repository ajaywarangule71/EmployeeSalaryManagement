package adminpanel;

public class AdminLogin {

	
	private int adid;
	private String username, password;

	public int getAdid() 
	{
		return adid;
	}

	public void setAdid(int adid) 
	{
		this.adid = adid;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "AdminLogin [adid=" + adid + ", username=" + username + ", password=" + password + "]";
	}

 }


