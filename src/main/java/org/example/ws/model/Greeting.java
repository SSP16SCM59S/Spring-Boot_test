package org.example.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greeting {

    @Id
    private Long stock_id;

    private String stock_name;
    
    private String stock_market;
    
    private String stock_price;
    
    private String last_update;

    public Greeting() 
    {

    }

	public Long getStock_id() {
		return stock_id;
	}

	public void setStock_id(Long stock_id) 
	{
		this.stock_id = stock_id;
	}

	public String getStock_name() 
	{
		return stock_name;
	}

	public void setStock_name(String stock_name) 
	{
		this.stock_name = stock_name;
	}

	public String getStock_market() 
	{
		return stock_market;
	}

	public void setStock_market(String stock_market) {
		
		this.stock_market = stock_market;
	}

	public String getStock_price()
	{
		return stock_price;
	}

	public void setStock_price(String stock_price) 
	{
		this.stock_price = stock_price;
	}

	public String getLast_update() 
	{
		return last_update;
	}

	public void setLast_update(String last_update) 
	{
		this.last_update = last_update;
	}

   

}
